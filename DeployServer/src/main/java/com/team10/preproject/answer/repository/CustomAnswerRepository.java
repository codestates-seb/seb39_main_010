package com.team10.preproject.answer.repository;

import com.team10.preproject.answer.entity.Answer;
import com.team10.preproject.question.dto.CommentsChildrenResponse;

import java.util.List;

public interface CustomAnswerRepository {

    List<Answer> findAnswerByQuestionId(Long questionId);
    List<CommentsChildrenResponse> findQuestionAnswers(Long questionId, Long answerId);
}
