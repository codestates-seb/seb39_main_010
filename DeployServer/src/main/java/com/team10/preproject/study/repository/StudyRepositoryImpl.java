package com.team10.preproject.study.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team10.preproject.study.dto.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.team10.preproject.member.entity.QMember.*;
import static com.team10.preproject.study.entity.QStudy.*;
import static com.team10.preproject.studycomment.entity.QStudyComment.*;


@RequiredArgsConstructor
public class StudyRepositoryImpl implements CustomStudyRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<StudyOneResponse> findOneStudyById(Long studyId, String orderCriteria) {

        Optional<StudyOneResponse> response = Optional.ofNullable(queryFactory
                .select(new QStudyOneResponse(
                        study.studyId,
                        study.title,
                        study.content,
                        member.nickname,
                        study.viewCount,
                        study.likeCount,
                        study.recruitment,
                        study.userLike,
                        study.createdAt,
                        study.updatedAt))
                .from(study)
                .innerJoin(study.member, member)
                .where(study.studyId.eq(studyId))
                .fetchOne());

        if (response.isEmpty()) {
            return Optional.empty();
        }

        List<StudyOneCommentResponse> studyComments = queryFactory
                .select(new QStudyOneCommentResponse(
                        studyComment.parent.studyCommentId,
                        studyComment.studyCommentId,
                        studyComment.comment,
                        member.nickname,
                        studyComment.likeCount,
                        studyComment.userLike,
                        studyComment.createdAt,
                        studyComment.updatedAt,
                        studyComment.isDeleted))
                .from(studyComment)
                .innerJoin(studyComment.study, study)
                .innerJoin(studyComment.member, member)
                .where(study.studyId.eq(studyId).and(studyComment.parent.isNull()))
                .orderBy(eqOrderCriteria(orderCriteria))
                .fetch();

        List<StudyCommentChildrenResponse> childComments = queryFactory
                .select(new QStudyCommentChildrenResponse(
                        studyComment.parent.studyCommentId,
                        studyComment.studyCommentId,
                        studyComment.comment,
                        member.nickname,
                        studyComment.createdAt,
                        studyComment.updatedAt,
                        studyComment.isDeleted))
                .from(studyComment)
                .innerJoin(studyComment.study, study)
                .innerJoin(studyComment.member, member)
                .where(study.studyId.eq(studyId).and(studyComment.parent.studyCommentId.isNotNull()))
                .fetch();

        studyComments.stream()
                .forEach(parent -> {
                    parent.setChildren(childComments.stream()
                            .filter(child -> child.getParentId().equals(parent.getStudyCommentId()))
                            .collect(Collectors.toList()));
                });

        response.get().setStudyComment(studyComments);
        return response;
    }

    private OrderSpecifier<?> eqOrderCriteria(String orderCriteria) {
        if(orderCriteria.contains("likecount")) {
            return studyComment.likeCount.desc();
        }else {
            return studyComment.studyCommentId.asc();
        }
    }


}
