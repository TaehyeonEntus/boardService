package com.taehyeon.boardService.service;

import com.taehyeon.boardService.entity.Member;
import com.taehyeon.boardService.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long join(Member member) {
        return memberRepository.save(member).getId();
    }

    public Member getMemberByName(String name) {
        return memberRepository.findByName(name);
    }
}
