package com.team10.preproject.question.controller;


import com.team10.preproject.global.dto.SingleResponseDto;
import com.team10.preproject.global.auth.PrincipalDetails;
import com.team10.preproject.question.dto.QuestionDto;
import com.team10.preproject.question.dto.QuestionOneResponse;
import com.team10.preproject.question.dto.QuestionResponseDto;
import com.team10.preproject.question.entity.Question;
import com.team10.preproject.question.mapper.QuestionMapper;
import com.team10.preproject.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionMapper mapper;

    @PostMapping
    public ResponseEntity questionWrite(@Valid @RequestBody QuestionDto.Post requestBody,
                                        @AuthenticationPrincipal PrincipalDetails principal) {

        Question question = mapper.questionPostToQuestion(requestBody);
        Question createQuestion = questionService.questionwrite(question, principal.getMember());
        QuestionResponseDto questionResponseDto = mapper.questionToQuestionResponse(createQuestion);

        questionResponseDto.setMemberId(principal.getMember().getMemberId());
        questionResponseDto.setNickname(principal.getMember().getNickname());

        return new ResponseEntity<>(
                new SingleResponseDto<>(questionResponseDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity questionList(@PageableDefault(size=5, sort="questionId", direction = Sort.Direction.DESC)
                                               Pageable pageable, String searchType, String keyword){

        Page<Question> questions = null;
        if(searchType == null || keyword == null){
            questions = questionService.questionList(pageable);
        } else{
            switch (searchType) {
                case "title":
                    questions = questionService.questionSearchTitle(keyword, pageable);
                    break;
                case "content":
                    questions = questionService.questionSearchContent(keyword, pageable);
                    break;
                case "tc":
                    questions = questionService.questionSearchTitleContent(keyword, keyword, pageable);
                    break;
                case "writer":
                    List<QuestionResponseDto> question = questionService.questionSearchWriter(keyword, pageable);
                    return new ResponseEntity<>(question, HttpStatus.OK);
            }
        }
        Page<QuestionResponseDto> pageDto = questions.map(QuestionResponseDto::new);
        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }

    @GetMapping("/{question-id}")
    public QuestionOneResponse questionView(@PathVariable("question-id") Long questionId) {

        QuestionOneResponse questionOneResponse = questionService.questionView(questionId);

        return questionOneResponse;
    }

    @DeleteMapping("/{question-id}")

    public ResponseEntity questionDelete(@PathVariable("question-id") Long questionId) {

        questionService.questionDelete(questionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/{question-id}")
    public ResponseEntity questionUpdate(@PathVariable("question-id") Long questionId,
                                         @Valid @RequestBody QuestionDto.Put requestBody){

        Question question = questionService.questionUpdate(questionId, mapper.questionPutToQuesiton(requestBody));
        QuestionResponseDto questionResponseDto = mapper.questionToQuestionResponse(question);
        questionResponseDto.setMemberId(question.getMember().getMemberId());
        questionResponseDto.setNickname(question.getMember().getNickname());

        return new ResponseEntity<>(
                new SingleResponseDto<>(questionResponseDto), HttpStatus.OK);
    }
}
