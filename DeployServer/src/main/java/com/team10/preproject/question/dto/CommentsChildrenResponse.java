package com.team10.preproject.question.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.team10.preproject.answer.entity.DeleteStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentsChildrenResponse {

    private Long parentId;
    private Long answerId;
    private String comment;
    private Long memberId;
    private String nickname;
    private DeleteStatus isDeleted;

    @QueryProjection
    public CommentsChildrenResponse(Long parentId, Long answerId, String comment, Long memberId, String nickname, DeleteStatus isDeleted) {

        this.parentId = parentId;
        this.answerId = answerId;
        this.comment = comment;
        this.memberId = memberId;
        this.nickname = nickname;
        this.isDeleted = isDeleted;
    }
}
