package com.team10.preproject.helper.email;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleEmailSendable implements EmailSendable{
    @Override
    public void send(String message) { log.info("Sent simple email!"); }
}
