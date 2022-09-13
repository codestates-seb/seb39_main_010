package com.team10.preproject.member.entity;

import com.team10.preproject.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
//@Data
public class Member extends Auditable {
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

//    @Enumerated(value = EnumType.STRING)
//    @Column(length = 20, nullable = false)
//    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    public  Member(String email) { this.email = email; }

    public  Member(String username, String email, String nickname, String password) {
        this.username = username;
        this.email = email;
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

