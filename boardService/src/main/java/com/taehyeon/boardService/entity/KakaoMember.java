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
@Table(name="KAKAO_MEMBER")
@DiscriminatorValue("K")
public class KakaoMember extends Member{
    @Column(nullable = false, unique = true)
    private Long kakaoMemberId;
}
