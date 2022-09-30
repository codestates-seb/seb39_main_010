package com.team10.preproject.answer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team10.preproject.global.audit.Auditable;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.question.entity.Question;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Answer extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Lob
    @Column(nullable = false)
    private String comment;

    @Enumerated(value = EnumType.STRING)
    private DeleteStatus isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnoreProperties({"password","createdAt","updatedAt","email","username","roles","roleList"})
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Answer parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Answer> children = new ArrayList<>();

    public static Answer createAnswer(String comment, Question question, Member member, Answer parent){

        Answer answer = new Answer();
        answer.comment = comment;
        answer.question = question;
        answer.member = member;
        answer.parent = parent;
        answer.isDeleted = DeleteStatus.N;

        return answer;
    }

    public void changeDeletedStatus(DeleteStatus deleteStatus){
        this.isDeleted = deleteStatus;
    }

}
