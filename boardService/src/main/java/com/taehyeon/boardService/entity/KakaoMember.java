package com.taehyeon.boardService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity // DB와 매핑될 객체인 것을 알려줌
@Table(name = "KAKAO_MEMBER")
@DiscriminatorValue("K")
@NoArgsConstructor
public class KakaoMember extends Member {
    @Column(nullable = false, unique = true)
    private Long kakaoMemberId;

    public KakaoMember(Long kakaoMemberId, String nickname) {
        this.kakaoMemberId = kakaoMemberId;
        this.nickname = nickname;
        this.role = Role.USER;
    }

    public Long getId() {
        return this.id;
    }
}
