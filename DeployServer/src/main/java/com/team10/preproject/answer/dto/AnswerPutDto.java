package com.team10.preproject.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerPutDto {
    private Long answerId;

    @NotBlank(message = "내용은 공백이 불가능 합니다.")
    private String comment;

}
