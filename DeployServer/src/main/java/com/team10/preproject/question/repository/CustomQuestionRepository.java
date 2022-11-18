package com.team10.preproject.question.repository;

import com.team10.preproject.question.dto.QuestionOneResponse;
import com.team10.preproject.question.dto.QuestionResponseDto;
import com.team10.preproject.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomQuestionRepository {

    Optional<QuestionOneResponse> findOneQuestionById(Long QuestionId, String orderCriteria);
    List<QuestionResponseDto> findCategoryQuestion(String category, String searchType,
                                        String keyword, Pageable pageable);
}
