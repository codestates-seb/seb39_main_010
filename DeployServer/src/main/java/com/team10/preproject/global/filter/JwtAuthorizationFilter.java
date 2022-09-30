package com.team10.preproject.global.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.team10.preproject.member.entity.Member;
import com.team10.preproject.member.repository.MemberRepository;
import com.team10.preproject.global.auth.PrincipalDetails;
import com.team10.preproject.token.entity.Token;
import com.team10.preproject.token.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository, TokenService tokenService) {

        super(authenticationManager);
        this.memberRepository = memberRepository;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("인증이나 권한이 필요한 주소 요청 됨.");
        String jwtHeader = request.getHeader("Authorization");
        try {
        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }
        String jwtToken = jwtHeader.replace("Bearer ", "");
        String email = JWT.require(Algorithm.HMAC512("cos_jwt_token")).build().verify(jwtToken).getClaim("email").asString();

        if (email != null) {
            Member memberEntity = memberRepository.findByEmail(email);
            PrincipalDetails principalDetails = new PrincipalDetails(memberEntity);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
            }
        else super.doFilterInternal(request, response, chain);
        }
        catch (TokenExpiredException ex) {
            System.out.println("Expired JWT Access token");
            String refreshToken = request.getHeader("Refresh");
            if(!tokenService.expiredToken(refreshToken)) {
                String email = tokenService.getEmail(refreshToken);
                tokenService.generateToken(email);
                Token newToken = tokenService.generateToken(email);
                String json = tokenService.addToken(newToken.getAccessToken(), newToken.getRefreshToken(), response);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(json);
            }else {
                System.out.println("Expired JWT Refresh token. Please log in.");
            }
        }
    }
}
