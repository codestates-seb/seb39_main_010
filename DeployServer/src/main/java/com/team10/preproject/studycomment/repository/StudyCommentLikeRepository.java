package com.team10.preproject.studycomment.repository;

import com.team10.preproject.studycomment.entity.StudyCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudyCommentLikeRepository extends JpaRepository<StudyCommentLike, Long> {

    @Query(value = "select case when count(s.study_comment_like_id) = 1 then 'true' else 'false' end " +
            "from study_comment_like s where member_id = :memberId and study_comment_like_id =:studyCommentLikeId",
            nativeQuery = true)
    boolean likeView(@Param(value = "studyCommentLikeId") Long studyCommentLikeId,
                     @Param(value = "memberId") Long memberId);
}
