package com.team10.preproject.studycomment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team10.preproject.global.audit.Auditable;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.study.entity.Study;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class StudyComment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyCommentId;

    @Lob
    @Column(nullable = false)
    private String comment;

    private int likeCount;

    private boolean userLike;

    @Enumerated(value = EnumType.STRING)
    private DeleteStatus isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    @JsonIgnore
    private Study study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnoreProperties({"password","createdAt","updatedAt","email","username","roles","roleList"})
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private StudyComment parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<StudyComment> children = new ArrayList<>();

    @OneToMany(mappedBy = "studyComment", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StudyCommentLike> studyCommentLikes = new ArrayList<>();

    public static StudyComment createStudyComment(String comment, Study study, Member member, StudyComment parent) {

        StudyComment studyComment = new StudyComment();
        studyComment.comment = comment;
        studyComment.study = study;
        studyComment.member = member;
        studyComment.parent = parent;
        studyComment.isDeleted = DeleteStatus.N;

        return studyComment;
    }

    public void changeDeletedStatus(DeleteStatus deleteStatus) {
        this.isDeleted = deleteStatus ;
    }

    public void mappingStudyCommentLike(StudyCommentLike studyCommentLike) {
        this.studyCommentLikes.add(studyCommentLike);
    }

    public void updateLikeCount() {
        this.likeCount = this.studyCommentLikes.size();
    }

    public void discountLike(StudyCommentLike studyCommentLike) {
        this.studyCommentLikes.remove(studyCommentLike);
    }
}