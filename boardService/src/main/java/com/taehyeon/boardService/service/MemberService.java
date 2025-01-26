package com.taehyeon.boardService.service;

import com.taehyeon.boardService.entity.KakaoMember;
import com.taehyeon.boardService.entity.Member;
import com.taehyeon.boardService.repository.KakaoMemberRepository;
import com.taehyeon.boardService.repository.LocalMemberRepository;
import com.taehyeon.boardService.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final KakaoMemberRepository kakaoMemberRepository;
    private final LocalMemberRepository localMemberRepository;

    public KakaoMember findKakaoMemberByKakaoMemberId(Long kakaoMemberId){
        return kakaoMemberRepository.findByKakaoMemberId(kakaoMemberId);
    }
}
