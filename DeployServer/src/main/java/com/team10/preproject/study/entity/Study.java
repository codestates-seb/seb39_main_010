package com.team10.preproject.study.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team10.preproject.global.audit.Auditable;
import com.team10.preproject.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Study extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyId;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;

    private boolean recruitment;

    private boolean userLike;

    @Column(name = "view_count", columnDefinition = "int default 0")
    private int viewCount;

    @Column(name = "like_count", columnDefinition = "int default 0")
    private int likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnoreProperties({"password","createdAt","updatedAt","email","username","roles","roleList"})
    private Member member;

    @OneToMany(mappedBy = "study", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("study")
    private List<StudyLike> studyLikes = new ArrayList<>();


    public void mappingStudyLike(StudyLike studyLike) {
        this.studyLikes.add(studyLike);
    }

    public void updateLikeCount() {
        this.likeCount = this.studyLikes.size();
    }

    public void discountLike(StudyLike studyLike) {
        this.studyLikes.remove(studyLike);
    }

    public void changeMember(Member member) {
        this.member = member;
    }
}
