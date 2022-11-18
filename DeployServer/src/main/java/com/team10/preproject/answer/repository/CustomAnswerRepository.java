package com.team10.preproject.answer.repository;

import com.team10.preproject.answer.dto.AnswerListResponseDto;
import com.team10.preproject.question.dto.CommentsChildrenResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomAnswerRepository {

    List<CommentsChildrenResponse> findQuestionAnswers(Long questionId, Long answerId);
    List<AnswerListResponseDto> findAnswerMember(Long memberId, Pageable pageable);
}
