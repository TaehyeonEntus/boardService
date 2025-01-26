package com.taehyeon.boardService.exception.memberExceptions;


public class MemberException extends RuntimeException {
    public MemberException() {
        super("Default Member Exception");
    }

    public MemberException(String msg) {
        super(msg);
    }
}
