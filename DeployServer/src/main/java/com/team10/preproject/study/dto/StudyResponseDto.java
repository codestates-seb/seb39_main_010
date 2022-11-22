package com.team10.preproject.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyResponseDto {

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
}
