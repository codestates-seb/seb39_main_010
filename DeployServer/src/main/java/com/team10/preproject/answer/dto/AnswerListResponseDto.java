package com.team10.preproject.answer.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.team10.preproject.answer.entity.DeleteStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerListResponseDto {
    private Long parentId;
    private Long answerId;
    private String comment;
    private String title;
    private String createdAt;
    private String updatedAt;
    private DeleteStatus isDeleted;

    @QueryProjection
    public AnswerListResponseDto(Long parentId, Long answerId, String comment, String title,
                                 String createdAt, String updatedAt, DeleteStatus isDeleted) {

        this.parentId = parentId;
        this.answerId = answerId;
        this.comment = comment;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }
}
