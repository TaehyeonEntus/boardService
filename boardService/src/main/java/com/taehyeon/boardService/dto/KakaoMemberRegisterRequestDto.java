package com.taehyeon.boardService.dto;

import lombok.Getter;

@Getter
public class KakaoMemberRegisterRequestDto {
    private Long kakaoMemberId;
    private String nickname;

    public KakaoMemberRegisterRequestDto(Long kakaoMemberId, String nickname) {
        this.kakaoMemberId = kakaoMemberId;
        this.nickname = nickname;
    }
}
