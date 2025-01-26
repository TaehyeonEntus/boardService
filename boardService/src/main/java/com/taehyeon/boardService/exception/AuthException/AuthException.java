package com.taehyeon.boardService.exception.AuthException;

public class AuthException extends RuntimeException {
    public AuthException() {
        super("Default Auth Exception");
    }

    public AuthException(String message) {
        super(message);
    }
}
