package com.team10.preproject.question.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchType {
    title, content, titleOrContent, writer
}
