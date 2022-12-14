package com.team10.preproject.global.auth.oauth.dto;

import com.team10.preproject.member.entity.Member;
import lombok.Getter;
import java.io.Serializable;

/**
 * 직렬화 기능을 가진 Member클래스
 */
@Getter
public class SessionUser implements Serializable {

    private final String nickname;
    private final String email;
    private final String picture;

    public SessionUser(Member member){

        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.picture = member.getPicture();
    }
}