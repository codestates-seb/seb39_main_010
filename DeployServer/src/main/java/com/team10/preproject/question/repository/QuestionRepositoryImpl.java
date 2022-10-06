package com.team10.preproject.question.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team10.preproject.question.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.team10.preproject.question.entity.QQuestion.*;
import static com.team10.preproject.member.entity.QMember.*;
import static com.team10.preproject.answer.entity.QAnswer.*;
import static com.team10.preproject.category.entity.QCategory.*;
import static com.team10.preproject.category.entity.QSubcategory.*;

@RequiredArgsConstructor
public class QuestionRepositoryImpl implements CustomQuestionRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<QuestionOneResponse> findOneQuestionById(Long questionId) {

        Optional<QuestionOneResponse> response = Optional.ofNullable(queryFactory
                .select(new QQuestionOneResponse(
                        question.questionId,
                        question.title,
                        question.content,
                        category.jobDomain,
                        subcategory.tag,
                        question.viewCount,
                        question.likeCount,
                        question.userLike,
                        question.createdAt,
                        question.updatedAt,
                        member.memberId,
                        member.nickname))
                .from(question)
                .innerJoin(question.member, member)
                .innerJoin(question.category, category)
                .innerJoin(question.tag, subcategory)
                .where(question.questionId.eq(questionId))
                .fetchOne());

        if (response.isEmpty()) {
            return Optional.empty();
        }

        List<QuestionOneCommentResponse> answers = queryFactory
                .select(new QQuestionOneCommentResponse(
                        answer.parent.answerId,
                        answer.answerId,
                        answer.comment,
                        answer.likeCount,
                        answer.userLike,
                        answer.createdAt,
                        answer.updatedAt,
                        member.memberId,
                        member.nickname,
                        answer.isDeleted))
                .from(answer)
                .innerJoin(answer.question, question)
                .innerJoin(question.member, member)
                .where(question.questionId.eq(questionId).and(answer.parent.isNull()))
                .orderBy(answer.answerId.asc())
                .fetch();

        List<CommentsChildrenResponse> childComments = queryFactory
                .select(new QCommentsChildrenResponse(
                        answer.parent.answerId,
                        answer.answerId,
                        answer.comment,
                        answer.createdAt,
                        answer.updatedAt,
                        member.memberId,
                        member.nickname,
                        answer.isDeleted))
                .from(answer)
                .innerJoin(answer.question, question)
                .innerJoin(answer.member, member)
                .where(question.questionId.eq(questionId).and(answer.parent.answerId.isNotNull()))
                .fetch();

        answers.stream()
                .forEach(parent -> {
                    parent.setChildren(childComments.stream()
                            .filter(child -> child.getParentId().equals(parent.getAnswerId()))
                            .collect(Collectors.toList()));
                });

        response.get().setAnswers(answers);

        return response;
    }

    @Override
    public List<QuestionResponseDto> findWriterQuestion(String keyWord, Pageable pageable) {

        List<QuestionResponseDto> response = queryFactory
                .select(new QQuestionResponseDto(
                        question.questionId,
                        question.title,
                        question.content,
                        category.jobDomain,
                        subcategory.tag,
                        question.viewCount,
                        question.likeCount,
                        question.userLike,
                        question.createdAt,
                        question.updatedAt,
                        question.member.memberId,
                        question.member.nickname))
                .from(question)
                .innerJoin(question.category, category)
                .innerJoin(question.tag, subcategory)
                .where(question.member.nickname.contains(keyWord))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        return response;
    }
}