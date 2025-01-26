package com.taehyeon.boardService.exception.AuthException;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidPasswordException extends AuthException {
    public InvalidPasswordException() {
        super("Invalid Password Exception");
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}
