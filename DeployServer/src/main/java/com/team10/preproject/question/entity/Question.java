package com.team10.preproject.question.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team10.preproject.answer.entity.Answer;
import com.team10.preproject.global.audit.Auditable;
import com.team10.preproject.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob  // 대용량 데이터
    private String content;

    @Column(name = "like_count", columnDefinition = "int default 0")
    private int likeCount;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonIgnoreProperties({"password","createdAt","updatedAt","email","username","roles","roleList"})
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY) // mappedBy - FK가 아니고 컬럼 생성 X
    @JsonIgnoreProperties("question") // 무한 참조 방지
//    @OrderBy("id desc")
    private List<Answer> answer;

    @Column(name = "view_count", columnDefinition = "int default 0")
    private int viewCount;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<QuestionLike> questionLikeList = new ArrayList<>();

    public void mappingQuestionLike(QuestionLike questionLike) {
        this.questionLikeList.add(questionLike);
    }

    public void updateLikeCount() {
        this.likeCount = this.questionLikeList.size();
    }

    public void discountLike(QuestionLike questionLike) {
        this.questionLikeList.remove(questionLike);
    }
}
