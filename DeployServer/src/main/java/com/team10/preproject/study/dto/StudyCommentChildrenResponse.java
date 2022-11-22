package com.team10.preproject.study.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.team10.preproject.studycomment.entity.DeleteStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudyCommentChildrenResponse {

    private Long parentId;
    private Long studyCommentId;
    private String comment;
    private String nickname;
    private String createdAt;
    private String updatedAt;
    private DeleteStatus isDeleted;


    @QueryProjection
    public StudyCommentChildrenResponse(Long parentId, Long studyCommentId, String comment, String nickname,
                                        String createdAt, String updatedAt, DeleteStatus isDeleted) {
        this.parentId = parentId;
        this.studyCommentId = studyCommentId;
        this.comment = comment;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }
}
