package com.team10.preproject.question.mapper;

import com.team10.preproject.global.qnaCategory.entity.Category;
import com.team10.preproject.global.qnaCategory.entity.Subcategory;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.question.dto.QuestionDto;
import com.team10.preproject.question.dto.QuestionResponseDto;
import com.team10.preproject.question.entity.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QuestionMapperClass {

    public Question questionPostToQuestion(QuestionDto.Post post) {
        Member member = Member.builder()
                .memberId(post.getMember().getMemberId())
                .nickname(post.getMember().getNickname())
                .build();
        Category category = Category.builder()
                .categoryId(post.getCategoryId())
                .build();
        Subcategory subcategory = Subcategory.builder()
                .subcategoryId(post.getTagId())
                .build();
        return Question.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .category(category)
                .tag(subcategory)
                .member(member)
                .build();
    }

    public Question questionPutToQuestion(QuestionDto.Put put) {
        Member member = Member.builder()
                .memberId(put.getMember().getMemberId())
                .nickname(put.getMember().getNickname())
                .build();
        Category category = Category.builder()
                .categoryId(put.getCategoryId())
                .build();
        Subcategory subcategory = Subcategory.builder()
                .subcategoryId(put.getTagId())
                .build();
        return Question.builder()
                .title(put.getTitle())
                .content(put.getContent())
                .category(category)
                .tag(subcategory)
                .member(member)
                .build();
    }

    public QuestionResponseDto questionToResponseDto(Question question) {
        return QuestionResponseDto.builder()
                .questionId(question.getQuestionId())
                .title(question.getTitle())
                .content(question.getContent())
                .category(question.getCategory().getJobDomain())
                .tag(question.getTag().getTag())
                .viewCount(question.getViewCount())
                .likeCount(question.getLikeCount())
                .memberId(question.getQuestionId())
                .nickname(question.getMember().getNickname())
                .createdAt(question.getCreatedAt())
                .updatedAt(question.getUpdatedAt())
                .build();
    }
}
