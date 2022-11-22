package com.team10.preproject.studycomment.controller;


import com.team10.preproject.global.auth.PrincipalDetails;
import com.team10.preproject.global.response.dto.SingleResponseDto;
import com.team10.preproject.studycomment.dto.StudyCommentPutDto;
import com.team10.preproject.studycomment.dto.StudyCommentRequestDto;
import com.team10.preproject.studycomment.dto.StudyCommentResponseDto;
import com.team10.preproject.studycomment.entity.StudyComment;
import com.team10.preproject.studycomment.mapper.StudyCommentMapper;
import com.team10.preproject.studycomment.service.StudyCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/studies")
public class StudyCommentController {

    private StudyCommentService studyCommentService;
    private StudyCommentMapper mapper;

    public StudyCommentController(StudyCommentService studyCommentService, StudyCommentMapper mapper) {
        this.studyCommentService = studyCommentService;
        this.mapper = mapper;
    }

    @PostMapping("/{study-id}/comments")
    public ResponseEntity studyCommentWrite(@PathVariable("study-id") Long studyId,
                                            @RequestBody StudyCommentRequestDto requestDto,
                                            @AuthenticationPrincipal PrincipalDetails principal) {

        return new ResponseEntity<>(new SingleResponseDto<>
                (studyCommentService.commentWrite(principal.getMember().getMemberId(),
                        studyId, requestDto)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{study-id}/comments/{study-comment-id}")
    public ResponseEntity studyCommentDelete(@PathVariable("study-comment-id") Long studyCommentId,
                                             @AuthenticationPrincipal PrincipalDetails principal) {

        studyCommentService.commentDelete(studyCommentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{study-id}/comments/{study-comment-id}")
    public ResponseEntity studyCommentUpdate(@PathVariable("study-comment-id") Long studyCommentId,
                                             @Valid @RequestBody StudyCommentPutDto putDto) {

        StudyComment studyComment = studyCommentService.commentUpdate(studyCommentId, mapper.commentPutToComment(putDto));
        StudyCommentResponseDto responseDto = mapper.commentResponseToDto(studyComment);

        return new ResponseEntity<>(
                new SingleResponseDto<>(responseDto), HttpStatus.OK);
    }

    @PostMapping("/{study-id}/sympathy/{study-comment-id}")
    public ResponseEntity studyCommentLike(@PathVariable("study-comment-id") Long studyCommentId,
                                           @AuthenticationPrincipal PrincipalDetails principal) {

        studyCommentService.commentLike(studyCommentId, principal.getMember().getMemberId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}