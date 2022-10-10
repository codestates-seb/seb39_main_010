package com.team10.preproject.question.repository;

import com.team10.preproject.question.dto.QuestionOneResponse;
import com.team10.preproject.question.dto.QuestionResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomQuestionRepository {

    Optional<QuestionOneResponse> findOneQuestionById(Long QuestionId);
    List<QuestionResponseDto> findWriterQuestion(String keyWord, Pageable pageable);

//    Optional<QuestionResponseDto> findCategoryQuestion(String title, String content, String writer, String category,
//                                                       String orderCriteria, Pageable pageable);

}
