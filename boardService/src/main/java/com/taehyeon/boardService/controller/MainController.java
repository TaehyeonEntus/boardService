package com.taehyeon.boardService.controller;

import com.taehyeon.boardService.aop.LoggedOutOnly;
import com.taehyeon.boardService.aop.NoCache;
import com.taehyeon.boardService.exception.memberExceptions.MemberException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@NoCache
@LoggedOutOnly
public class MainController {
    @Value("${kakao.client_id}")
    private String clientId;

    @Value("${kakao.redirect_url}")
    private String redirectUrl;

    @GetMapping("/login")
    public String loginPage(Model model, HttpSession httpSession, HttpServletResponse response) {
        Long memberId = (Long)httpSession.getAttribute("memberId");

        String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+clientId+"&redirect_uri="+redirectUrl;
        model.addAttribute("location", location);
        return "login";
    }

    @GetMapping("/kakaoRegister")
    public String kakaoRegister() throws MemberException {
        return "kakaoRegister";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
