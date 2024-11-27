package com.volunticket.domain.post.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PostType {
    VOLUNTEER("봉사"),
    PERFORMANCE("공연");

    private final String description;
}
