package com.team10.preproject.answer.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team10.preproject.answer.dto.AnswerListResponseDto;
import com.team10.preproject.answer.dto.QAnswerListResponseDto;
import com.team10.preproject.answer.entity.Answer;
import com.team10.preproject.question.dto.CommentsChildrenResponse;
import com.team10.preproject.question.dto.QCommentsChildrenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.team10.preproject.answer.entity.QAnswer.*;
import static com.team10.preproject.member.entity.QMember.*;
import static com.team10.preproject.question.entity.QQuestion.*;

@RequiredArgsConstructor
public class AnswerRepositoryImpl implements CustomAnswerRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentsChildrenResponse> findQuestionAnswers(Long questionId, Long answerId) {

        return queryFactory.select(new QCommentsChildrenResponse(
                        answer.parent.answerId,
                        answer.answerId,
                        answer.comment,
                        answer.createdAt,
                        answer.updatedAt,
                        member.memberId,
                        member.nickname, answer.isDeleted))
                .from(answer)
                .innerJoin(answer.parent)
                .innerJoin(answer.question, question)
                .innerJoin(question.member, member)
                .where(question.questionId.eq(questionId).and(answer.parent.answerId.eq(answerId)))
                .orderBy(answer.answerId.asc())
                .fetch();
    }

    @Override
    public List<AnswerListResponseDto> findAnswerMember(Long memberId, Pageable pageable) {

        return queryFactory.select(new QAnswerListResponseDto(
                answer.parent.answerId,
                answer.answerId,
                answer.comment,
                question.title,
                answer.createdAt,
                answer.updatedAt,
                answer.isDeleted))
                .from(answer)
                .innerJoin(answer.question, question)
                .where(answer.member.memberId.eq(memberId))
                .orderBy(answer.answerId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
