package com.taehyeon.boardService.dto;

import lombok.Getter;

@Getter
public class PostSaveRequestDto {
    private Long authorId;
    private String title;
    private String content;

    public PostSaveRequestDto(Long authorId, String title, String content) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
    }
}
