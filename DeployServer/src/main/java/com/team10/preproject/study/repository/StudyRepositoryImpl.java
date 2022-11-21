package com.team10.preproject.study.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team10.preproject.study.dto.QStudyOneResponse;
import com.team10.preproject.study.dto.StudyOneResponse;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.team10.preproject.study.entity.QStudy.*;

@RequiredArgsConstructor
public class StudyRepositoryImpl implements CustomStudyRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<StudyOneResponse> findOneStudyById(Long studyId) {

        Optional<StudyOneResponse> response = Optional.ofNullable(queryFactory
                .select(new QStudyOneResponse(
                        study
                )))

        return Optional.empty();
    }
}
