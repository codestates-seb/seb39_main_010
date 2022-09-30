package com.team10.preproject.token.controller;

import com.team10.preproject.token.entity.Token;
import com.team10.preproject.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class TokenController {

    private final TokenService tokenService;

    @GetMapping("/token/refresh")
    public String refreshAuth(HttpServletRequest request, HttpServletResponse response) {

        String token = request.getHeader("Refresh");

        if (token != null && !tokenService.expiredToken(token)) {

            String email = tokenService.getEmail(token);
            Token newToken = tokenService.generateToken(email);
            return tokenService.addToken(newToken.getAccessToken(), newToken.getRefreshToken(), response);
        }

        throw new RuntimeException();
    }
}