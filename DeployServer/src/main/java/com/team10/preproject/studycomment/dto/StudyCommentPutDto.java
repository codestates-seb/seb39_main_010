package com.team10.preproject.studycomment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudyCommentPutDto {

    private Long studyCommentId;

    @NotBlank(message = "내용은 공백이 불가능 합니다.")
    private String comment;
}
