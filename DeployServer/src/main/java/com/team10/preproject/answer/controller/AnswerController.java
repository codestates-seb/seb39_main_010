package com.team10.preproject.answer.controller;



import com.team10.preproject.answer.dto.AnswerPutDto;
import com.team10.preproject.answer.dto.AnswerResponseDto;
import com.team10.preproject.answer.entity.Answer;
import com.team10.preproject.answer.mapper.AnswerMapper;
import com.team10.preproject.answer.service.AnswerService;
import com.team10.preproject.dto.SingleResponseDto;
import com.team10.preproject.oauth.PrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/questions")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private AnswerMapper mapper;

    @PostMapping("/{question-id}/answers")
    public ResponseEntity answerWrite(@PathVariable("question-id") Long questionId, @RequestBody Answer requestAnswer,
                                      @AuthenticationPrincipal PrincipalDetails principal){

        Answer answer = answerService.anserWrite(principal.getMember(), questionId ,requestAnswer);
        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }

    @DeleteMapping("/{question-id}/answers/{answer-id}")
    public ResponseEntity answerDelete(@PathVariable("answer-id") Long answerId){

        answerService.anserDelete(answerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{question-id}/answers/{answer-id}")
    public ResponseEntity answerUpdate(@PathVariable("answer-id") Long answerId,
                                       @Valid @RequestBody AnswerPutDto answerPutDto){

        Answer answer = answerService.answerUpdate(answerId, mapper.answerPutToAnswer(answerPutDto));
        AnswerResponseDto answerResponseDto = mapper.answerResponseToDto(answer);
        answerResponseDto.setNickname(answer.getMember().getNickname());
        return new ResponseEntity<>(
                new SingleResponseDto<>(answerResponseDto),HttpStatus.OK);
    }

}
