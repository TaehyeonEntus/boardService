package com.taehyeon.boardService.dto;

import lombok.Getter;

@Getter
public class PostSaveRequestDto {
    private Long memberId;
    private String title;
    private String content;

    public PostSaveRequestDto(Long memberId, String title, String content) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
    }
}
