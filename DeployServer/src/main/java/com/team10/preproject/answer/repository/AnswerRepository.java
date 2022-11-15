package com.team10.preproject.answer.repository;

import com.team10.preproject.answer.dto.AnswerListResponseDto;
import com.team10.preproject.answer.entity.Answer;
import com.team10.preproject.question.dto.QuestionOneCommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long>, CustomAnswerRepository {

    @Query(value = "select case when count(a.answer_id) = 1 then 'true' else 'false' end " +
            "from answer a where parent_id =:parentId and is_deleted = 'N'", nativeQuery = true)
    boolean findByLast(@Param("parentId") Long parentId);

    @Query(value = "select a1.* from answer a1 where parent_id = :parentId and answer_id not in (:answerId)" +
            "and is_deleted = 'N'", nativeQuery = true)
    List<Answer> findByIsDeletedNotIn(@Param("parentId") Long parentId, @Param("answerId") Long answerId);

    @Query(value = "select a1.* from answer a1 where parent_id = :parentId " +
            "and is_deleted = 'N'", nativeQuery = true)
    List<Answer> findByIsDeleted(@Param("parentId") Long parentId);

//    @Query(value = "select * from answer where member_id = :memberId",
//            countQuery = "select count(*) from answer q where member_id = :memberId", nativeQuery = true)
//    Page<Answer> findByAnswerAndMember(@Param(value = "memberId") Long memberId, Pageable pageable);

}
