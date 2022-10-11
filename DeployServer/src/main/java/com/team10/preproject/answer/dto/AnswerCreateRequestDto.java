package com.team10.preproject.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerCreateRequestDto {

    private String comment;
    private Long parentId;
}
