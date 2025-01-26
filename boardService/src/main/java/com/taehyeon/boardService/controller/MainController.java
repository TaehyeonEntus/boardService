package com.taehyeon.boardService.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Value("${kakao.client_id}")
    private String clientId;

    @Value("${kakao.redirect_url}")
    private String redirectUrl;

    @GetMapping("/login")
    public String loginPage(Model model) {
        String location = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+clientId+"&redirect_uri="+redirectUrl;
        model.addAttribute("location", location);
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
