package com.team10.preproject.question.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOneResponse {

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

    private List<QuestionOneCommentResponse> answers = new ArrayList<>();


    @QueryProjection
    public QuestionOneResponse(Long questionId, String title, String content, String category, String tag,
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
}
