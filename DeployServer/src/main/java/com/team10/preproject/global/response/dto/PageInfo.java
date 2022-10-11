package com.team10.preproject.global.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PageInfo {

    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
