package com.team10.preproject.answer.repository;

import com.team10.preproject.answer.entity.Answer;
import com.team10.preproject.answer.entity.AnswerLike;
import com.team10.preproject.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerLikeRepository extends JpaRepository<AnswerLike, Long> {
    Optional<AnswerLike> findByAnswerAndMember(Answer answer, Member member);
}
