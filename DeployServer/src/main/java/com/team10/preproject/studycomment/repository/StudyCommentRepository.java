package com.team10.preproject.studycomment.repository;

import com.team10.preproject.studycomment.entity.StudyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyCommentRepository extends JpaRepository<StudyComment,Long>, CustomStudyCommentRepository {

}
