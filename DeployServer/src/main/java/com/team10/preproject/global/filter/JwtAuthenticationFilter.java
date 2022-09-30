package com.team10.preproject.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.global.auth.PrincipalDetails;
import com.team10.preproject.token.entity.Token;
import com.team10.preproject.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            ObjectMapper om = new ObjectMapper();
            Member member = om.readValue(request.getInputStream(), Member.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());

            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        System.out.println("successfulAuthentication");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        Long memberId = principalDetails.getMemberId();
        String username = principalDetails.getUsername();
        String nickname = principalDetails.getNickname();
        String email = principalDetails.getEmail();
        String json =
                "{\"memberId\":" + memberId + ",\n\"username\":\"" + username + "\",\n\"email\":\"" + email + "\",\n\"nickname\":\"" + nickname + "\"}";
        Token jwtToken = tokenService.generateToken(email);
        response.addHeader("Authorization", jwtToken.getAccessToken());
        response.addHeader("Refresh", jwtToken.getRefreshToken());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}