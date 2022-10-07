package com.team10.preproject.question.entity;

import com.team10.preproject.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public static boolean isVotedQuestion(Optional<QuestionLike> optionalQuestionLike) {
        return optionalQuestionLike.isPresent();
    }

    public void mappingMember(Member member) {
        this.member = member;
        member.mappingQuestionLike(this);
    }

    public void mappingQuestion(Question question) {
        this.question = question;
        question.mappingQuestionLike(this);
    }
}
