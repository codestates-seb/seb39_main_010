package com.team10.preproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MainProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainProjectApplication.class, args);
    }

}
