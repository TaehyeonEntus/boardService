package com.taehyeon.boardService.entity;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
