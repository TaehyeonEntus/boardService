package com.taehyeon.boardService.repository;

import com.taehyeon.boardService.entity.KakaoMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KakaoMemberRepository extends JpaRepository<KakaoMember, Long> {
    KakaoMember findByKakaoMemberId(Long kakaoId);
}
