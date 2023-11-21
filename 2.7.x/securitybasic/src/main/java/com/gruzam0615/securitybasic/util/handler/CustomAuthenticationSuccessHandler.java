package com.gruzam0615.securitybasic.util.handler;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.gruzam0615.securitybasic.config.security.CustomUserDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails u = (CustomUserDetails) authentication.getPrincipal();
        
        if(u != null) {
            log.info("(로그인 성공) 사용자: {} 시간: {}", u.getUsername(), LocalDateTime.now());
            response.sendRedirect("/");
        }
        else {
            log.error("(로그인 실패) 사용자: {} 의 정보가 없습니다.");
            response.sendRedirect("/sign/signInPage");
        }

    }

}
