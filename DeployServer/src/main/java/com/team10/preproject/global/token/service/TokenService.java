package com.team10.preproject.global.token.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.team10.preproject.global.token.entity.Token;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class TokenService {

    public Token generateToken(String email) {

        long accessTokenPeriod = 1000L * 6;
//        0L * 10L;
        long refreshTokenPeriod = 1000L * 60L * 60L;


        String accessToken = JWT.create()
                .withSubject("cos jwt token")
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenPeriod))
                .withClaim("email", email)
                .sign(Algorithm.HMAC512("cos_jwt_token"));
        String refreshToken = JWT.create()
                .withSubject("cos jwt token")
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenPeriod))
                .withClaim("email", email)
                .sign(Algorithm.HMAC512("cos_jwt_token"));
        return new Token("Bearer " + accessToken, refreshToken);
    }

    public String addToken(String accessToken, String refreshToken, HttpServletResponse response){

        response.addHeader("Authorization", accessToken);
        response.addHeader("Refresh", refreshToken);

        return "HAPPY NEW TOKEN";
    }

    public boolean expiredToken(String jwtToken) {

        try {
            Date claim = (Date) JWT.require(Algorithm.HMAC512("cos_jwt_token")).build().verify(jwtToken).getClaim("exp");

            return claim.before(new Date());

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
