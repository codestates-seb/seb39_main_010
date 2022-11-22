package com.team10.preproject.studycomment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team10.preproject.study.dto.QStudyCommentChildrenResponse;
import com.team10.preproject.study.dto.StudyCommentChildrenResponse;
import lombok.RequiredArgsConstructor;
import java.util.List;


@RequiredArgsConstructor
public class StudyCommentRepositoryImpl implements CustomStudyCommentRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StudyCommentChildrenResponse> findStudyComment(Long studyId, Long studyCommentId) {
        return null;

    }
}
