package com.team10.preproject.study.repository;

import com.team10.preproject.study.dto.StudyOneResponse;

import java.util.Optional;

public interface CustomStudyRepository {

    Optional<StudyOneResponse> findOneStudyById(Long studyId);
}
