package com.team10.preproject.global.token.controller;

import com.team10.preproject.global.token.entity.Token;
import com.team10.preproject.global.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("api/v1/token/refresh")
    public String refreshAuth(HttpServletRequest request, HttpServletResponse response) {

        String token = request.getHeader("Refresh");

        if (token != null && tokenService.validateToken(token)) {

            String email = tokenService.getEmail(token);
            Token newToken = tokenService.generateToken(email);

            return tokenService.addToken(newToken.getAccessToken(), newToken.getRefreshToken(), response);
        }
        throw new RuntimeException();
    }
}