package com.team10.preproject.question.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.team10.preproject.question.entity.Question;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@Data
@NoArgsConstructor
public class QuestionResponseDto {

    private Long questionId;
    private String title;
    private String content;
    private String category;
    private String tag;
    private int viewCount;
    private int likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long memberId;
    private String nickname;

    @QueryProjection
    public QuestionResponseDto(Long questionId, String title, String content, String category, String tag,
                               int viewCount, int likeCount, LocalDateTime createdAt, LocalDateTime updatedAt,
                               Long memberId, String nickname) {

        this.questionId = questionId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.tag = tag;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.memberId = memberId;
        this.nickname = nickname;
    }

    public QuestionResponseDto(Question question) {
        this.questionId = question.getQuestionId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.category = question.getCategory().getJobDomain();
        this.tag = question.getTag().getTag();
        this.viewCount = question.getViewCount();
        this.likeCount = question.getLikeCount();
        this.createdAt = question.getCreatedAt();
        this.updatedAt = question.getUpdatedAt();
        this.memberId = question.getMember().getMemberId();
        this.nickname = question.getMember().getNickname();
    }
}
