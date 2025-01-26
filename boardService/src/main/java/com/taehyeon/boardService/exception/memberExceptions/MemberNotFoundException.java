package com.taehyeon.boardService.exception.memberExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends MemberException{
    public MemberNotFoundException() {
        super("Member not found");
    }

    public MemberNotFoundException(String message) {
        super(message);
    }
}
