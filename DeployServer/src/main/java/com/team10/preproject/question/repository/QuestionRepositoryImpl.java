package com.team10.preproject.question.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team10.preproject.global.qnaCategory.entity.QCategory;
import com.team10.preproject.question.dto.*;
import com.team10.preproject.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.team10.preproject.global.qnaCategory.entity.QCategory.*;
import static com.team10.preproject.question.entity.QQuestion.*;
import static com.team10.preproject.member.entity.QMember.*;
import static com.team10.preproject.answer.entity.QAnswer.*;
import static com.team10.preproject.global.qnaCategory.entity.QSubcategory.*;

@RequiredArgsConstructor
public class QuestionRepositoryImpl implements CustomQuestionRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<QuestionOneResponse> findOneQuestionById(Long questionId, String orderCriteria) {

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
                .innerJoin(answer.member, member)
                .where(question.questionId.eq(questionId).and(answer.parent.isNull()))
                .orderBy(eqOrderCriteria(orderCriteria))
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
    public List<QuestionResponseDto> findCategoryQuestion(String category, String searchType,
                                               String keyword, Pageable pageable) {

        List<QuestionResponseDto> response = queryFactory
                .select(new QQuestionResponseDto(
                        question.questionId,
                        question.title,
                        question.content,
                        QCategory.category.jobDomain,
                        subcategory.tag,
                        question.viewCount,
                        question.likeCount,
                        question.userLike,
                        question.createdAt,
                        question.updatedAt,
                        member.memberId,
                        member.nickname))
                .from(question)
                .innerJoin(question.category, QCategory.category)
                .innerJoin(question.tag, subcategory)
                .innerJoin(question.member, member)
                .where(eqCategory(category), eqsearchType(searchType, keyword))
                .orderBy(getOrderSpecifier(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return response;
    }

    private OrderSpecifier<?> eqOrderCriteria(String orderCriteria) {
        if(orderCriteria.contains("likeCount")) {
            return answer.likeCount.desc();
        }else {
            return answer.answerId.asc();
        }
    }

    private BooleanExpression eqCategory(String category) {
        if(StringUtils.isEmpty(category)) {
            return null;
        }
        return QCategory.category.jobDomain.eq(category);
    }

    private BooleanExpression eqsearchType(String searchType, String keyword) {
        if(StringUtils.isEmpty(searchType)) {
            return null;
        } else if(searchType.contains("title")) {
            return question.title.contains(keyword);
        } else if(searchType.contains("content")) {
            return question.content.contains(keyword);
        } else if(searchType.contains("tc")) {
            return question.title.contains(keyword).or(question.content.contains(keyword));
        } else if(searchType.contains("writer")) {
            return question.member.nickname.eq(keyword);
        } else {
            return null;
        }
    }

    private List<OrderSpecifier> getOrderSpecifier(Sort sort) {
        List<OrderSpecifier> orders = new ArrayList<>();

        sort.stream().forEach(order ->  {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();
            PathBuilder orderByExpression = new PathBuilder(Question.class, "question");
            orders.add(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });
        return orders;
    }
}
