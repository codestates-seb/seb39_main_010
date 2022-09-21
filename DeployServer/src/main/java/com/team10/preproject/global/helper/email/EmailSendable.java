package com.team10.preproject.global.helper.email;

import org.springframework.stereotype.Component;

@Component
public interface EmailSendable {
    void send(String message) throws InterruptedException;
}
