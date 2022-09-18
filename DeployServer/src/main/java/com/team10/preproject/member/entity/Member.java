package com.team10.preproject.member.entity;

import com.team10.preproject.audit.Auditable;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@EqualsAndHashCode(callSuper=true)
public class Member extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long memberId;
    private String roles;

    @Column(nullable = false, updatable = false, unique = true)
    private  String email;

    @Column(nullable = false, updatable = false, unique = true)
    private  String username;

    @Column(length = 20, nullable = false)
    private  String nickname;

    @Column(nullable = false)
    private  String password;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    private boolean enabled;

    private String provider;
    private String providerId;

//    @Enumerated(value = EnumType.STRING)
//    @Column(length = 20, nullable = false)
//    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    @Builder
    public Member(String username, String email, String role, String provider, String providerId, String nickname, String password) {
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.provider = provider;
        this.providerId = providerId;
        this.nickname = nickname;
        this.password = password;
    }

    public List<String> getRoleList() {
        if(this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
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

