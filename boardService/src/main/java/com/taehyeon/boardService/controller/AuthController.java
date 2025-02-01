package com.taehyeon.boardService.controller;

import com.taehyeon.boardService.aop.LoggedInOnly;
import com.taehyeon.boardService.aop.LoggedOutOnly;
import com.taehyeon.boardService.aop.NoCache;
import com.taehyeon.boardService.dto.KakaoMemberRegisterRequestDto;
import com.taehyeon.boardService.dto.KakaoUserInfoResponseDto;
import com.taehyeon.boardService.dto.LocalMemberRegisterRequestDto;
import com.taehyeon.boardService.dto.LocalMemberLoginDto;
import com.taehyeon.boardService.entity.KakaoMember;
import com.taehyeon.boardService.exception.memberExceptions.MemberException;
import com.taehyeon.boardService.service.AuthService;
import com.taehyeon.boardService.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@NoCache
@LoggedOutOnly
public class AuthController {
    private final AuthService authService;
    private final MemberService memberService;

    @GetMapping("/kakaoLogin")
    public String kakaoLogin(@RequestParam String code, HttpSession httpSession) throws MemberException {
        String accessToken = authService.getAccessTokenFromKakao(code);
        KakaoUserInfoResponseDto kakaoUserInfo = authService.getKakaoUserInfo(accessToken);
        Long kakaoMemberId = kakaoUserInfo.getId();
        KakaoMember kakaoMember = memberService.findKakaoMemberByKakaoMemberId(kakaoMemberId);

        //이미 존재하는 사용자
        if (kakaoMember != null) {
            Long memberId = kakaoMember.getId();
            httpSession.setAttribute("memberId", memberId);
            return "redirect:/login";
        }
        //존재 하지 않는 회원이면 가입
        else{
            httpSession.setAttribute("kakaoMemberId", kakaoMemberId);
            return "redirect:/kakaoRegister";
        }
    }

    @PostMapping("/kakaoRegister")
    public String kakaoRegister(@RequestParam String nickname,
                                HttpSession httpSession) throws MemberException {
        Long kakaoMemberId = (Long) httpSession.getAttribute("kakaoMemberId");

        authService.registerKakaoMember(new KakaoMemberRegisterRequestDto(kakaoMemberId, nickname));
        return "redirect:/login";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String nickname,
                           @RequestParam String password,
                           @RequestParam String passwordConfirm) throws MemberException {
        LocalMemberRegisterRequestDto localMemberRegisterRequestDto = new LocalMemberRegisterRequestDto(username, nickname, password, passwordConfirm);
        authService.registerLocalMember(localMemberRegisterRequestDto);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession httpSession){
        Long memberId = authService.loginLocalMember(new LocalMemberLoginDto(username, password));
        httpSession.setAttribute("memberId", memberId);
        return "redirect:/board";
    }
}
