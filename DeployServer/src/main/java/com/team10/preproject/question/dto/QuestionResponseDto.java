package com.team10.preproject.question.dto;

import com.team10.preproject.answer.dto.AnswerResponseDto;
import com.team10.preproject.answer.entity.Answer;
import com.team10.preproject.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDto {
    private Long questionId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long memberId;
    private String email;
    private String nickname;

    public QuestionResponseDto(Question question) {
        this.questionId = question.getQuestionId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.createdAt = question.getCreatedAt();
        this.updatedAt = question.getUpdatedAt();
        this.memberId = question.getMember().getMemberId();
        this.email = question.getMember().getEmail();
        this.nickname = question.getMember().getNickname();
    }
}


//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class QuestionAnswerResponseDto {
//        private Long questionId;
//        private String title;
//        private String content;
//        private List answerId;
//        private String comment;
//        private LocalDateTime createdAt;
//        private LocalDateTime updatedAt;
//        private Long memberId;
//        private String email;
//        private String nickname;
//
//
//        public QuestionAnswerResponseDto(Question question, Answer answer) {
//            this.questionId = question.getQuestionId();
//            this.title = question.getTitle();
//            this.content = question.getContent();
//            this.createdAt = question.getCreatedAt();
//            this.updatedAt = question.getUpdatedAt();
//            this.memberId = question.getMember().getMemberId();
//            this.email = question.getMember().getEmail();
//            this.nickname = question.getMember().getNickname();
//
//            this.answerId = question.getAnswer().stream().map(AnswerResponseDto::new).collect(Collectors.toList());
//            this.comment = answer.getComment();
//            this.createdAt = answer.getCreatedAt();
//            this.updatedAt = answer.getUpdatedAt();
//            this.memberId = answer.getMember().getMemberId();
//            this.email = answer.getMember().getEmail();
//            this.nickname = answer.getMember().getNickname();
//
//            ////        question.getAnswer().stream().map(AnswerResponseDto::new).collect(Collectors.toList());
//        }
//    }
