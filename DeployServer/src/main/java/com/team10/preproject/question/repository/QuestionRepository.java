package com.team10.preproject.question.repository;

import com.team10.preproject.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepository extends JpaRepository<Question,Long>, CustomQuestionRepository {

    @Modifying
    @Query("update Question set view_count = view_count + 1 where questionId = :questionId")
    int updateView(@Param(value = "questionId") Long questionId);

    @Query(value = "select * from question where member_id = :memberId",
            countQuery = "select count(*) from question q where member_id = :memberId", nativeQuery = true)
    Page<Question> findByQuestionAndMember(@Param(value = "memberId") Long memberId, Pageable pageable);
}
