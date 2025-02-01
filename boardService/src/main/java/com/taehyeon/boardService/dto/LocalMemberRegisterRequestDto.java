package com.taehyeon.boardService.dto;

import lombok.Getter;

@Getter
public class LocalMemberRegisterRequestDto {
    private String username;

    private String nickname;

    private String password;

    private String passwordConfirm;

    public LocalMemberRegisterRequestDto(String username, String nickname, String password, String passwordConfirm) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }
}
