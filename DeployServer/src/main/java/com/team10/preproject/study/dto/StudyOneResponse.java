package com.team10.preproject.study.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StudyOneResponse {

    private Long studyId;
    private String title;
    private String content;
    private String nickname;
    private int viewCount;
    private int likeCount;
    private boolean recruitment;
    private boolean userLike;
    private String createdAt;
    private String updatedAt;

    private List<StudyOneCommentResponse> studyComment = new ArrayList<>();

    @QueryProjection
    public StudyOneResponse(Long studyId, String title, String content, String nickname, int viewCount, int likeCount,
                            boolean recruitment, boolean userLike, String createdAt, String updatedAt) {
        this.studyId = studyId;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.recruitment = recruitment;
        this.userLike = userLike;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void changeUserLike(boolean userLike) {
        this.userLike = userLike;
    }
}
