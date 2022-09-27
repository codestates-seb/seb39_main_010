package com.team10.preproject.oauth.dto;

import com.team10.preproject.member.entity.Member;
import lombok.Getter;

import java.io.Serializable;

/**
 * 직렬화 기능을 가진 Member클래스
 */
@Getter
public class SessionUser implements Serializable {

    private String nickname;
    private String email;
    private String picture;

    public SessionUser(Member member){

        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.picture = member.getPicture();
    }
}