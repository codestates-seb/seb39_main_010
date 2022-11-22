package com.team10.preproject.study.repository;

import com.team10.preproject.member.entity.Member;
import com.team10.preproject.study.entity.Study;
import com.team10.preproject.study.entity.StudyLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudyLikeRepository extends JpaRepository<StudyLike, Long> {

    Optional<StudyLike> findByStudyAndMember(Study study, Member member);

    @Query(value = "select case when count(s.study_like_id) = 1 then 'true' else 'false' end " +
            "from study_like s where member_id = :memberId and study_id =:studyId", nativeQuery = true)
    boolean likeView(@Param(value = "studyId") Long studyId, @Param(value = "memberId") Long memberId);
}
