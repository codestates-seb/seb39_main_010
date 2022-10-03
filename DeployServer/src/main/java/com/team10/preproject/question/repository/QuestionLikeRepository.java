package com.team10.preproject.question.repository;

import com.team10.preproject.member.entity.Member;
import com.team10.preproject.question.entity.Question;
import com.team10.preproject.question.entity.QuestionLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionLikeRepository extends JpaRepository<QuestionLike, Long> {
    Optional<QuestionLike> findByQuestionAndMember(Question question, Member member);
}
