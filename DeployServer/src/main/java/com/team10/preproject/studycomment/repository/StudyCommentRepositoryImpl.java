package com.team10.preproject.studycomment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team10.preproject.study.dto.QStudyCommentChildrenResponse;
import com.team10.preproject.study.dto.StudyCommentChildrenResponse;
import lombok.RequiredArgsConstructor;
import java.util.List;

import static com.team10.preproject.study.entity.QStudy.*;
import static com.team10.preproject.member.entity.QMember.*;
import static com.team10.preproject.studycomment.entity.QStudyComment.*;

@RequiredArgsConstructor
public class StudyCommentRepositoryImpl implements CustomStudyCommentRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StudyCommentChildrenResponse> findStudyComment(Long studyId, Long studyCommentId) {

        return queryFactory.select(new QStudyCommentChildrenResponse(
                        studyComment.parent.studyCommentId,
                        studyComment.studyCommentId,
                        studyComment.comment,
                        member.nickname,
                        studyComment.createdAt,
                        studyComment.updatedAt,
                        studyComment.isDeleted))
                .from(studyComment)
                .innerJoin(studyComment.parent)
                .innerJoin(studyComment.study, study)
                .innerJoin(study.member, member)
                .where(study.studyId.eq(studyId).and(studyComment.parent.studyCommentId.eq(studyCommentId)))
                .orderBy(studyComment.studyCommentId.asc())
                .fetch();
    }
}
