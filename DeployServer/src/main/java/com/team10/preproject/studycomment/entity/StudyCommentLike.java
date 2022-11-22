package com.team10.preproject.studycomment.entity;

import com.team10.preproject.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StudyCommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyCommentLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_comment_id")
    private StudyComment studyComment;

    public void mappingMember(Member member) {
        this.member = member;
        member.mappingStudyCommentLike(this);
    }

    public void mappingStudyComment(StudyComment studyComment) {
        this.studyComment = studyComment;
        studyComment.mappingStudyCommentLike(this);
    }
}
