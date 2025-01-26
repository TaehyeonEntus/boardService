package com.taehyeon.boardService.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class RegisterRequestDto {
    private String username;

    private String password;

    private String passwordConfirm;

    public RegisterRequestDto(String username, String password, String passwordConfirm) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }
}
