package com.team10.preproject.answer.dto;

import com.team10.preproject.answer.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseDto {
    private Long answerId;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String nickname;


    public AnswerResponseDto(Answer answer) {
        this.answerId = answer.getAnswerId();
        this.comment = answer.getComment();
        this.createdAt = answer.getCreatedAt();
        this.updatedAt = answer.getUpdatedAt();
        this.nickname = answer.getMember().getNickname();
    }
}

