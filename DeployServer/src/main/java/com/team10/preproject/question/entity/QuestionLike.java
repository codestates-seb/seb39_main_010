package com.team10.preproject.question.entity;

import com.team10.preproject.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.Optional;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class QuestionLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    public void mappingMember(Member member) {
        this.member = member;
        member.mappingQuestionLike(this);
    }

    public void mappingQuestion(Question question) {
        this.question = question;
        question.mappingQuestionLike(this);
    }
}
