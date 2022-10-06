package com.team10.preproject.member.oauth.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.member.repository.MemberRepository;
import com.team10.preproject.member.token.entity.Token;
import com.team10.preproject.member.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
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

    private final TokenService tokenService;

    private final ObjectMapper objectMapper;

    private  final MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        Member member = memberRepository.findByEmail(email);
        Token token = tokenService.generateToken(member.getEmail());
        log.info("{}", token);
        writeTokenResponse(response, token, member);
    }

    private void writeTokenResponse(HttpServletResponse response, Token token, Member member)
            throws IOException {

        response.addHeader("Authorization", token.getAccessToken());
        response.addHeader("Refresh", token.getRefreshToken());
        response.setContentType("application/json;charset=UTF-8");
        Long memberId = member.getMemberId();
        String username = member.getUsername();
        String email = member.getEmail();
        String nickname = member.getNickname();
        String json =
                "{\"memberId\":" + memberId + ",\n\"username\":\"" + username + "\",\n\"email\":\"" + email + "\",\n\"nickname\":\"" + nickname + "\"}";

        var writer = response.getWriter();
        writer.println(objectMapper.writeValueAsString(token));
        writer.write(json);
        writer.flush();
    }
}