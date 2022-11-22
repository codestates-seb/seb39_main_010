package com.team10.preproject.studycomment.repository;

import com.team10.preproject.study.dto.StudyCommentChildrenResponse;

import java.util.List;

public interface CustomStudyCommentRepository {

    List<StudyCommentChildrenResponse> findStudyComment(Long studyId, Long studyCommentId);
}
