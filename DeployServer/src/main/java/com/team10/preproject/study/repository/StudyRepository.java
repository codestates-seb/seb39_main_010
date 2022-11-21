package com.team10.preproject.study.repository;

import com.team10.preproject.study.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<Study,Long>, CustomStudyRepository{
}
