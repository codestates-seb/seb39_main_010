package com.team10.preproject.helper.email;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemplateEmailSendable implements EmailSendable{
    @Override
    public void send(String message) {
        // 템플릿을 사용한 이메일을 보낼 수 있습니다.
    }
}
