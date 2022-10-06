package com.team10.preproject.question.repository;

import com.team10.preproject.member.entity.Member;
import com.team10.preproject.question.entity.Question;
import com.team10.preproject.question.entity.QuestionLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionLikeRepository extends JpaRepository<QuestionLike, Long> {

    Optional<QuestionLike> findByQuestionAndMember(Question question, Member member);

    @Query(value = "select case when count(q.question_like_id) = 1 then 'true' else 'false' end " +
            "from question_like q where member_id = :memberId and question_id =:questionId", nativeQuery = true)
    boolean likeView(@Param(value = "questionId") Long questionId, @Param(value = "memberId") Long memberId);

}
