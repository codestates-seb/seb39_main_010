package com.team10.preproject.study.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.team10.preproject.studycomment.entity.DeleteStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StudyOneCommentResponse {

    private Long parentId;
    private Long studyCommentId;
    private String comment;
    private String nickname;
    private int likeCount;
    private boolean userLike;
    private String createdAt;
    private String updatedAt;
    private DeleteStatus isDeleted;
    private List<StudyCommentChildrenResponse> children = new ArrayList<>();

    @QueryProjection
    public StudyOneCommentResponse(Long parentId, Long studyCommentId, String comment, String nickname,
                                   int likeCount, boolean userLike, String createdAt, String updatedAt,
                                   DeleteStatus isDeleted) {
        this.parentId = parentId;
        this.studyCommentId = studyCommentId;
        this.comment = comment;
        this.nickname = nickname;
        this.likeCount = likeCount;
        this.userLike = userLike;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

    public void changeUserLike(boolean userLike) {
        this.userLike = userLike;
    }
}
