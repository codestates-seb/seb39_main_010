package com.team10.preproject.studycomment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudyCommentResponseDto {

    private Long studyCommentId;
    private String comment;
    private String createdAt;
    private String updatedAt;
    private String nickname;
}
