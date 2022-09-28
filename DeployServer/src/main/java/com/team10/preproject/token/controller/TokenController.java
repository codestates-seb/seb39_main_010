package com.team10.preproject.token.controller;

import com.team10.preproject.token.entity.Token;
import com.team10.preproject.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/token/expired")
    public String auth() {
        throw new RuntimeException();
    }

    @GetMapping("/token/refresh")
    public String refreshAuth(HttpServletRequest request, HttpServletResponse response) {

        String token = request.getHeader("Refresh");

        if (token != null && tokenService.verifyToken(token)) {

            String email = tokenService.getEmail(token);
            Long memberId = tokenService.getMemberId(token);
            String nickname = tokenService.getNickname(token);
            Token newToken = tokenService.generateToken(memberId, email, nickname);

            response.addHeader("Authorization", newToken.getAccessToken());
            response.addHeader("Refresh", newToken.getRefreshToken());
            response.setContentType("application/json;charset=UTF-8");

            return "HAPPY NEW TOKEN";
        }

        throw new RuntimeException();
    }
}