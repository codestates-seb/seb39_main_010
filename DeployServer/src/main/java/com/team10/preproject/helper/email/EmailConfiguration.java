package com.team10.preproject.helper.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EmailConfiguration {
    @Bean
    public EmailSendable emailSendable() { return new MockEmailSendable(); }

    @Primary
    @Bean
    public EmailSendable simpleEmailSendable() { return new SimpleEmailSendable(); }
}
