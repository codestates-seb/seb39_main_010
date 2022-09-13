package com.team10.preproject.question.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerResponseDto {
    private Long questionId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long memberId;
    private String nickname;
    private Long answerId;
    private String comment;
    private LocalDateTime answerCreatedAt;
    private LocalDateTime answerUpdatedAt;
    private Long answerMemberId;
    private String answerNickname;
}
