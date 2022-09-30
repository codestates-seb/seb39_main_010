package com.team10.preproject.question.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.team10.preproject.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class QuestionResponseDto {

    private Long questionId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long memberId;
    private String nickname;

    @QueryProjection
    public QuestionResponseDto(Long questionId, String title, String content, LocalDateTime createdAt,
                               LocalDateTime updatedAt, Long memberId, String nickname) {

        this.questionId = questionId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.memberId = memberId;
        this.nickname = nickname;
    }

    public QuestionResponseDto(Question question) {
        this.questionId = question.getQuestionId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.createdAt = question.getCreatedAt();
        this.updatedAt = question.getUpdatedAt();
        this.memberId = question.getMember().getMemberId();
        this.nickname = question.getMember().getNickname();
    }
}
