package com.team10.preproject.token.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.team10.preproject.token.entity.Token;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class TokenService {

    public Token generateToken(Long memberId, String email, String nickname) {

        long accessTokenPeriod = 1000L * 60L * 10L;
        long refreshTokenPeriod = 1000L * 60L * 60L;

        String accessToken = JWT.create()
                .withSubject("cos jwt token")
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenPeriod))
                .withClaim("memberId", memberId)
                .withClaim("email", email)
                .withClaim("nickname", nickname)
                .sign(Algorithm.HMAC512("cos_jwt_token"));
        String refreshToken = JWT.create()
                .withSubject("cos jwt token")
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenPeriod))
                .withClaim("memberId", memberId)
                .withClaim("email", email)
                .withClaim("nickname", nickname)
                .sign(Algorithm.HMAC512("cos_jwt_token"));
        return new Token("Bearer " + accessToken, refreshToken);
    }

    public boolean verifyToken(String jwtToken) {

        try {
            Date claim = (Date) JWT.require(Algorithm.HMAC512("cos_jwt_token")).build().verify(jwtToken).getClaim("exp");

            return claim.after(new Date());

            } catch (Exception e) {
                return false;
        }

    }

    public String getEmail(String token) {
        return JWT.require(Algorithm.HMAC512("cos_jwt_token")).build().verify(token).getClaim("email").asString();
    }

    public Long getMemberId(String token) {
        return JWT.require(Algorithm.HMAC512("cos_jwt_token")).build().verify(token).getClaim("memberId").asLong();
    }

    public String getNickname(String token) {
        return JWT.require(Algorithm.HMAC512("cos_jwt_token")).build().verify(token).getClaim("nickname").asString();
    }
}