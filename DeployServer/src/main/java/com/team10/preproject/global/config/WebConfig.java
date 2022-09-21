package com.team10.preproject.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Value("${config.domain}")
//    private String domain;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("domain")
//                .allowCredentials(true)
                .exposedHeaders("authorization")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS");

    }
}
