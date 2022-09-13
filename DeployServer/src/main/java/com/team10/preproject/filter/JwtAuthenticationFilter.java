package com.team10.preproject.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.oauth.PrincipalDetails;
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
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper om = new ObjectMapper();
            Member member = om.readValue(request.getInputStream(), Member.class);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            return authentication;
        } catch (IOException e) {
            e.printStackTrace();;
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        System.out.println("successfulAuthentication");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.findAndRegisterModules();

        Long memberId = principalDetails.getMember().getMemberId();
        String username = principalDetails.getMember().getUsername();
        String nickname = principalDetails.getMember().getNickname();

        String json =
                "{\"memberId\":" + memberId + ",\n\"email\":\"" + username + "\",\n\"nickname\":\"" + nickname + "\"}";

        String jwtToken = JWT.create()
                .withSubject("cos jwt token")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000 * 10)))
                .withClaim("id", memberId)
                .withClaim("username", username)
                .sign(Algorithm.HMAC512("cos_jwt_token"));
        response.addHeader("Authorization", "Bearer " + jwtToken);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}