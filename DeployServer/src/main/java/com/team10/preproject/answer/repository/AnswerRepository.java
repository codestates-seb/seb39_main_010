package com.team10.preproject.answer.repository;

import com.team10.preproject.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long>, CustomAnswerRepository {

    @Query("select c from Answer c left join fetch c.parent where c.answerId = :answerId")
    Optional<Answer> findAnswerByIdWithParent(@Param("answerId") Long answerId);

    @Query(value = "select a1.* from answer a1 where parent_id = :parentId and answer_id not in (:answerId)" +
            "and is_deleted = 'N'", nativeQuery = true)
    List<Answer> findByIsDeletedNotIn(@Param("parentId") Long parentId, @Param("answerId") Long answerId);

    @Query(value = "select a1.* from answer a1 where parent_id = :parentId " +
            "and is_deleted = 'N'", nativeQuery = true)
    List<Answer> findByIsDeleted(@Param("parentId") Long parentId);
}
