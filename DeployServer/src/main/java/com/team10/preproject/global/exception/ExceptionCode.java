package com.team10.preproject.global.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409, "Member exists"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_MEMBER_STATUS(400, "Invalid member status"),
    INVALID_MEMBER_AUTHENTICATION(400, "Invalid member authentication"),
    NoSuchElementException(500, "No value present");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int code, String message) {

        this.status = code;
        this.message = message;
    }
}
