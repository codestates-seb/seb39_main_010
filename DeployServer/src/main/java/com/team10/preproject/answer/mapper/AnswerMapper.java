package com.team10.preproject.answer.mapper;


import com.team10.preproject.answer.dto.AnswerPutDto;
import com.team10.preproject.answer.dto.AnswerResponseDto;
import com.team10.preproject.answer.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {
    AnswerResponseDto answerResponseToDto(Answer answer);
    Answer answerPostToAnswer(Answer answer);
    Answer answerPutToAnswer(AnswerPutDto answerPutDto);

}
