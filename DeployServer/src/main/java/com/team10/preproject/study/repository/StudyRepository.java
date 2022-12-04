package com.team10.preproject.study.repository;

import com.team10.preproject.study.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<Study,Long>, CustomStudyRepository{

    @Modifying
    @Query("update Study set view_count = view_count + 1 where studyId = :studyId")
    int updateView(@Param(value = "studyId") Long studyId);
}
