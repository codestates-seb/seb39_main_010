package com.team10.preproject.answer.repository;

import com.team10.preproject.answer.entity.Answer;
import com.team10.preproject.answer.entity.AnswerLike;
import com.team10.preproject.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerLikeRepository extends JpaRepository<AnswerLike, Long> {

    Optional<AnswerLike> findByAnswerAndMember(Answer answer, Member member);

    @Query(value = "select case when count(a.answer_like_id) = 1 then 'true' else 'false' end " +
            "from answer_like a where member_id = :memberId and answer_id =:answerId", nativeQuery = true)
    boolean likeView(@Param(value = "answerId") Long answerId, @Param(value = "memberId") Long memberId);
}
