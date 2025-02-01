package com.taehyeon.boardService.dto;

import lombok.Getter;

@Getter
public class LocalMemberLoginDto {
    private String username;
    private String password;

    public LocalMemberLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
