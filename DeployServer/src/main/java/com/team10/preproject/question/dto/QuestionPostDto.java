package com.team10.preproject.question.dto;

import com.team10.preproject.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class QuestionPostDto {

    private Long id;

    @NotBlank(message = "제목은 공백이 불가능 합니다.")
    private String title;

    @NotBlank(message = "내용은 공백이 불가능 합니다.")
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Member member;

}
