package com.taehyeon.boardService.aop;

import com.taehyeon.boardService.exception.AuthException.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

@Aspect
@Component
public class LoginCheckAspect {
    @Before("@within(com.taehyeon.boardService.aop.LoggedInOnly)")
    public void checkLogin() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute("memberId");
        if(memberId == null) {
            throw new AuthException("로그인 후 접근할 수 있는 페이지 입니다.");
        }
    }

    @Before("@within(com.taehyeon.boardService.aop.LoggedOutOnly)")
    public void checkLogout() throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getResponse();

        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute("memberId");
        if(memberId != null) {
            response.sendRedirect("/board");
        }
    }

}
