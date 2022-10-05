package com.team10.preproject.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:/secrets.properties")
public class WebConfig implements WebMvcConfigurer {

    @Value("${spring.config.domain}")
    private String domain;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .exposedHeaders("authorization", "refresh")
                .allowCredentials(true)
                .allowedOriginPatterns(domain)
                .allowedMethods("GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS");
    }
}
