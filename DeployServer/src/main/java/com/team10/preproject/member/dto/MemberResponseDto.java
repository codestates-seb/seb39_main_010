package com.team10.preproject.member.dto;

import com.team10.preproject.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MemberResponseDto {

    private long memberId;
    private String username;
    private String email;
    private String nickname;
    private String picture;
    private String favoriteCompany;
    private String selfIntroductions;
//    private Member.MemberStatus memberStatus;
//    public String getMemberStatus() { return memberStatus.getStatus(); }

    public void setMemberId(long memberId) {

        this.memberId = memberId;
    }
}