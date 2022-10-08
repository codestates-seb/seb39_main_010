package com.team10.preproject.global.config;

import com.team10.preproject.global.auth.oauth.service.CustomOAuth2UserService;
import com.team10.preproject.global.filter.JwtAuthenticationFilter;
import com.team10.preproject.global.filter.JwtAuthorizationFilter;
import com.team10.preproject.member.repository.MemberRepository;
import com.team10.preproject.global.auth.oauth.service.OAuth2SuccessHandler;
import com.team10.preproject.global.token.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public SecurityConfig(MemberRepository memberRepository, CustomOAuth2UserService customOAuth2UserService, OAuth2SuccessHandler successHandler, TokenService tokenService) {
        this.memberRepository = memberRepository;
        this.customOAuth2UserService = customOAuth2UserService;
        this.successHandler = successHandler;
        this.tokenService = tokenService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final MemberRepository memberRepository;

    private final CustomOAuth2UserService customOAuth2UserService;

    private final OAuth2SuccessHandler successHandler;

    private final TokenService tokenService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .cors()
                .and()
                .formLogin().disable()
                .logout().disable()
                .httpBasic().disable()
                .apply(new CustomDsl())
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/users/login/**",
                        "/api/v1/users/logout",
                        "/api/v1/users/signup",
                        "/api/v1/users/verification",
                        "/api/v1/users/forgot-password",
                        "/token/**",
                        "/api/v1/users/exists-username",
                        "/api/v1/users/exists-email",
                        "/api/v1/users/exists-nickname",
                        "/",
                        "/h2/**")
                .permitAll()
                .antMatchers("/api/v1/users/**",
                        "/api/v1/questions/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .successHandler(successHandler)
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

        return http.build();
    }

    public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {

        @Override
        public void configure(HttpSecurity builder) throws Exception {

            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, tokenService);
            jwtAuthenticationFilter.setFilterProcessesUrl("/api/v1/users/login");
            builder
                    .addFilter(jwtAuthenticationFilter)
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, memberRepository, tokenService));
        }
    }
}
