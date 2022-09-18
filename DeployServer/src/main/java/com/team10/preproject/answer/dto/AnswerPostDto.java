package com.team10.preproject.answer.dto;

import com.team10.preproject.answer.entity.Answer;
import com.team10.preproject.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerPostDto {
    private Long answerId;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String answerWriter;


    public AnswerPostDto(Answer answer) {
        this.answerId = answer.getAnswerId();
        this.comment = answer.getComment();
        this.createdAt = answer.getCreatedAt();
        this.updatedAt = answer.getUpdatedAt();
        this.answerWriter = answer.getMember().getNickname();
    }

    public boolean isSameWrite(Member loginUser) {
        return this.answerWriter.equals(loginUser.getNickname());
    }
}
