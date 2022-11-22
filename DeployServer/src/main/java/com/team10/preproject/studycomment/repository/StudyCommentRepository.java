package com.team10.preproject.studycomment.repository;

import com.team10.preproject.answer.entity.Answer;
import com.team10.preproject.studycomment.entity.StudyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyCommentRepository extends JpaRepository<StudyComment,Long>, CustomStudyCommentRepository {

    @Query(value = "select case when count(s.study_comment_id) = 1 then 'true' else 'false' end " +
            "from study_comment s where parent_id =:parentId and is_deleted = 'N'", nativeQuery = true)
    boolean findByLast(@Param("parentId") Long parentId);

    @Query(value = "select s.* from study_comment s where parent_id = :parentId and study_comment_id not in (:studyCommentId)" +
            "and is_deleted = 'N'", nativeQuery = true)
    List<Answer> findByIsDeletedNotIn(@Param("parentId") Long parentId, @Param("studyCommentId") Long studyCommentId);

    @Query(value = "select s.* from study_comment s where parent_id = :parentId " +
            "and is_deleted = 'N'", nativeQuery = true)
    List<Answer> findByIsDeleted(@Param("parentId") Long parentId);
}
