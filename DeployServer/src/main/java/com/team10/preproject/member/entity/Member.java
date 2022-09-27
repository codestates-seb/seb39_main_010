package com.team10.preproject.member.entity;

import com.team10.preproject.global.audit.Auditable;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Member extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(updatable = false, unique = true)
    private String username;

    @Column(length = 20, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    @Column
    private String favoriteCompany;

    @Column
    private String picture;

    @Lob
    private String selfIntroductions;

    private boolean enabled;
    private String provider;
    private String providerId;

//    @Enumerated(value = EnumType.STRING)
//    @Column(length = 20, nullable = false)
//    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    @Builder
    public Member(String username, String email, Role role, String provider, String providerId, String picture, String nickname, String password) {

        this.username = username;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.nickname = nickname;
        this.password = password;
        this.picture = picture;
        this.favoriteCompany = favoriteCompany;
        this.selfIntroductions = selfIntroductions;
    }

    public Member update(String picture){
        this.picture = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

    public List<String> getRoleList() {

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

