package com.taehyeon.boardService.aop;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class NoCacheAspect {
    @Before("@within(com.taehyeon.boardService.aop.NoCache)")
    public void noCache() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getResponse();
        if (response != null) {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
        }
    }
}
