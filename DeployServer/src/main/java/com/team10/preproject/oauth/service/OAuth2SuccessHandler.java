package com.team10.preproject.oauth.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.oauth.dto.OAuthAttributes;
import com.team10.preproject.token.entity.Token;
import com.team10.preproject.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        Member member = Member.builder()
                .email(oAuth2User.getAttribute("email"))
                .memberId(oAuth2User.getAttribute("memberId"))
                .nickname(oAuth2User.getAttribute("nickname"))
                .build();
        Token token = tokenService.generateToken(member.getMemberId(), member.getEmail(), member.getNickname());
        log.info("{}", token);

        writeTokenResponse(response, token);
    }

    private void writeTokenResponse(HttpServletResponse response, Token token)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.addHeader("Authorization", token.getAccessToken());
        response.addHeader("Refresh", token.getRefreshToken());
        response.setContentType("application/json;charset=UTF-8");

        var writer = response.getWriter();
        writer.println(objectMapper.writeValueAsString(token));
        writer.flush();
    }
}