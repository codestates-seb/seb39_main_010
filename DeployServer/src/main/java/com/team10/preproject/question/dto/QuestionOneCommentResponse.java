package com.team10.preproject.question.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.team10.preproject.answer.entity.DeleteStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class QuestionOneCommentResponse {

    private Long parentId;
    private Long answerId;
    private String comment;
    private int likeCount;
    private boolean userLike;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long memberId;
    private String nickname;
    private DeleteStatus isDeleted;
    private List<CommentsChildrenResponse> children = new ArrayList<>();

    @QueryProjection
    public QuestionOneCommentResponse(Long parentId, Long answerId, String comment, int likeCount,
                                      boolean userLike, LocalDateTime createdAt, LocalDateTime updatedAt,
                                      Long memberId, String nickname, DeleteStatus isDeleted) {

        this.parentId = parentId;
        this.answerId = answerId;
        this.comment = comment;
        this.likeCount = likeCount;
        this.userLike = userLike;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.memberId = memberId;
        this.nickname = nickname;
        this.isDeleted = isDeleted;
    }

    public void changeUserLike(boolean userLike) {
        this.userLike = userLike;
    }
}
