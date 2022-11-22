package com.team10.preproject.studycomment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudyCommentRequestDto {
    private Long parentId;
    private String comment;
}
