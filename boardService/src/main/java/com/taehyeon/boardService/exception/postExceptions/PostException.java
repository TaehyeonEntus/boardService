package com.taehyeon.boardService.exception.postExceptions;

public class PostException extends RuntimeException {
    public PostException() {
        super("Default Post Exception");
    }

    public PostException(String msg) {
        super(msg);
    }
}
