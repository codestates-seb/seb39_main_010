package com.team10.preproject.answer.controller;



import com.team10.preproject.answer.dto.AnswerCreateRequestDto;
import com.team10.preproject.answer.dto.AnswerListResponseDto;
import com.team10.preproject.answer.dto.AnswerPutDto;
import com.team10.preproject.answer.dto.AnswerResponseDto;
import com.team10.preproject.answer.entity.Answer;
import com.team10.preproject.answer.mapper.AnswerMapper;
import com.team10.preproject.answer.service.AnswerService;
import com.team10.preproject.global.response.dto.SingleResponseDto;
import com.team10.preproject.global.auth.PrincipalDetails;
import com.team10.preproject.question.dto.QuestionOneCommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/questions")
public class AnswerController {

    private AnswerService answerService;
    private AnswerMapper mapper;

    public AnswerController(AnswerService answerService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.mapper = mapper;
    }

    @PostMapping("/{question-id}/answers")
    public ResponseEntity answerWrite(@PathVariable("question-id") Long questionId,
                                                    @RequestBody AnswerCreateRequestDto requestDto,
                                                    @AuthenticationPrincipal PrincipalDetails principal) {

        return new ResponseEntity<>(new SingleResponseDto<>
                (answerService.anwserWrite(principal.getMember().getMemberId(), questionId, requestDto)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{question-id}/answers/{answer-id}")
    public ResponseEntity answerDelete(@PathVariable("answer-id") Long answerId,
                                       @AuthenticationPrincipal PrincipalDetails principal) {

        answerService.answerDelete(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{question-id}/answers/{answer-id}")
    public ResponseEntity answerUpdate(@PathVariable("answer-id") Long answerId,
                                       @Valid @RequestBody AnswerPutDto answerPutDto) {

        Answer answer = answerService.answerUpdate(answerId, mapper.answerPutToAnswer(answerPutDto));
        AnswerResponseDto answerResponseDto = mapper.answerResponseToDto(answer);
        answerResponseDto.setNickname(answer.getMember().getNickname());

        return new ResponseEntity<>(
                new SingleResponseDto<>(answerResponseDto),HttpStatus.OK);
    }

    @PostMapping("/{question-id}/sympathy/{answer-id}")
    public ResponseEntity answerLike(@PathVariable("answer-id") Long answerId,
                                     @AuthenticationPrincipal PrincipalDetails principal) {

        answerService.answerLike(answerId, principal.getMember().getMemberId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/member-answerlist")
    public ResponseEntity memberAnswerList(@PageableDefault(size = 5, direction = Sort.Direction.DESC) Pageable pageable,
                                             @AuthenticationPrincipal PrincipalDetails principal) {

        Page<AnswerListResponseDto> pageDto =
                answerService.memberAnswerList(principal.getMember().getMemberId(), pageable);
        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }
}
