package com.team10.preproject.answer.entity;

import com.team10.preproject.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AnswerLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerLikeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    public void mappingMember(Member member) {
        this.member = member;
        member.mappingAnswerLike(this);
    }

    public void mappingAnswer(Answer answer) {
        this.answer = answer;
        answer.mappingAnswerLike(this);
    }
}
