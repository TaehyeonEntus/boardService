package com.taehyeon.boardService.Controller;

import com.taehyeon.boardService.entity.Member;
import com.taehyeon.boardService.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/member/add")
    public void member(String name) {
        memberService.join(new Member(name));
    }
}
