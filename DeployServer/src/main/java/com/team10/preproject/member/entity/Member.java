package com.team10.preproject.member.entity;

import com.team10.preproject.answer.entity.AnswerLike;
import com.team10.preproject.global.audit.Auditable;
import com.team10.preproject.question.entity.QuestionLike;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Member extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 20, unique = true, updatable = false)
    private String username;

    @Column(length = 20, nullable = false)
    private String nickname;

    private String password;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    @Column(length = 200)
    private String favoriteCompany;

    @Lob
    private String picture;

    @Column(length = 200)
    private String selfIntroductions;

    private boolean enabled;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<QuestionLike> questionLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<AnswerLike> answerLikeList = new ArrayList<>();

//    @Enumerated(value = EnumType.STRING)
//    @Column(length = 20, nullable = false)
//    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    @Builder
    public Member(Long memberId, String username, String email, Role role, boolean enabled, String verificationCode, String nickname, String password, String picture, String favoriteCompany, String selfIntroductions) {

        this.memberId = memberId;
        this.username = username;
        this.email = email;
        this.role = role;
        this.nickname = nickname;
        this.password = password;
        this.picture = picture;
        this.favoriteCompany = favoriteCompany;
        this.selfIntroductions = selfIntroductions;
        this.enabled = enabled;
        this.verificationCode = verificationCode;
    }

    public void mappingQuestionLike(QuestionLike questionLike) {
        this.questionLikeList.add(questionLike);
    }

    public void mappingAnswerLike(AnswerLike answerLike) { this.answerLikeList.add(answerLike); }

    public void updatePicture(String picture){
        this.picture = picture;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

    public List<String> getRoleList() {

        if(this.role != null) {
            return List.of(this.role.getKey());
        }
        return new ArrayList<>();
    }

//    public enum  MemberStatus {
//        MEMBER_ACTIVE("활동중"),
//        MEMBER_SLEEP("휴면 상태"),
//        MEMBER_QUIT("탈퇴 상태");
//
//        @Getter
//        private String status;
//
//        MemberStatus(String status) { this.status = status; }
//    }
}

