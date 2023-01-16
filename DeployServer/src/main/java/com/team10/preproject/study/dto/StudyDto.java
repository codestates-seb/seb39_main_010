package com.team10.preproject.study.dto;

import com.team10.preproject.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class StudyDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {

        @NotBlank(message = "제목은 공백이 불가능 합니다.")
        private String title;

        @NotBlank(message = "내용은 공백이 불가능 합니다.")
        private String content;

        private Member member;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Put {

        private Long studyId;

        @NotBlank(message = "제목은 공백이 불가능 합니다.")
        private String title;

        @NotBlank(message = "내용은 공백이 불가능 합니다.")
        private String content;

        private boolean recruitment;

        private Member member;
    }
}
