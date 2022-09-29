package com.team10.preproject.question.repository;

import com.team10.preproject.question.dto.QuestionOneResponse;

import java.util.Optional;

public interface CustomQuestionRepository {

    Optional<QuestionOneResponse> findOneQuestionById(Long QuestionId);
}
