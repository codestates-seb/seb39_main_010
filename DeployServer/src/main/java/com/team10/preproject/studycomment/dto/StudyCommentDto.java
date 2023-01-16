package com.team10.preproject.studycomment.dto;

import com.team10.preproject.studycomment.entity.DeleteStatus;
import com.team10.preproject.studycomment.entity.StudyComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudyCommentDto {

    private Long studyCommentId;
    private String comment;
    private String nickname;
    private List<StudyComment> children = new ArrayList<>();

//    public StudyCommentDto(Long studyCommentId, String comment, String nickname) {
//        this.studyCommentId = studyCommentId;
//        this.comment = comment;
//        this.nickname = nickname;
//    }
//
//    public static StudyCommentDto convertCommentToDto(StudyComment studyComment) {
//
//        return studyComment.getIsDeleted() == DeleteStatus.Y ?
//                new StudyCommentDto(studyComment.getStudyCommentId(), "삭제된 댓글입니다.", null, null) :
//
//    }
}
