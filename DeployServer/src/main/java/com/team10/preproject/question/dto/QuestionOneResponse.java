package com.team10.preproject.question.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionOneResponse {

    private Long questionId;
    private String title;
    private String content;
    private Long memberId;
    private String nickname;

    private List<QuestionOneCommentResponse> answers = new ArrayList<>();


    @QueryProjection
    public QuestionOneResponse(Long questionId, String title, String content, Long memberId, String nickname) {

        this.questionId = questionId;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.nickname = nickname;
    }
}
