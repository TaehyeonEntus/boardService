package com.taehyeon.boardService.exception.postExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PostNotFoundException extends PostException {
    public PostNotFoundException() {
        super("Post not found");
    }

    public PostNotFoundException(String msg) {
        super(msg);
    }
}
