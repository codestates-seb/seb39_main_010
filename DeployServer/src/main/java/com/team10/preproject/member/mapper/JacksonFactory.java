package com.team10.preproject.member.mapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
//time table mapper로 사용가능
public class JacksonFactory {

    private static ObjectMapper objectMapper = null;
    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
        }

        return objectMapper;
    }
}
