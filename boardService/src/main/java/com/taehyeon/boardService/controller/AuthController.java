package com.taehyeon.boardService.controller;

import com.taehyeon.boardService.dto.KakaoUserInfoResponseDto;
import com.taehyeon.boardService.entity.KakaoMember;
import com.taehyeon.boardService.exception.memberExceptions.MemberException;
import com.taehyeon.boardService.service.AuthService;
import com.taehyeon.boardService.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final MemberService memberService;

    @GetMapping("/kakaoLogin")
    public String kakaoLogin(@RequestParam String code, HttpSession httpSession) throws MemberException {
        String accessToken = authService.getAccessTokenFromKakao(code);
        KakaoUserInfoResponseDto kakaoUserInfo = authService.getKakaoUserInfo(accessToken);
        Long kakaoMemberId = kakaoUserInfo.getId();
        KakaoMember kakaoMember = memberService.findKakaoMemberByKakaoMemberId(kakaoMemberId);

        //이미 존재하는 사용자라면 board로
        if (kakaoMember != null) {
            return "redirect:/board";
        }
        //존재 하지 않는 회원이면 가입
        else{
            return "redirect:/kakaoRegister";
        }
    }

    @GetMapping("/kakaoRegister}")
    public String kakaoRegister() throws MemberException {
        return "kakaoRegister";
    }
}
