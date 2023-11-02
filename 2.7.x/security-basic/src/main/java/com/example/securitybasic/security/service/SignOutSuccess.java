package com.example.securitybasic.security.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SignOutSuccess implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(
        HttpServletRequest request, 
        HttpServletResponse response, 
        Authentication authentication
    ) throws IOException, ServletException {
        if(authentication != null) {
            log.info("사용자 {} 가 로그아웃 합니다.", authentication.getName());
            response.sendRedirect("/");
        }
        else {
            log.info("이미 로그아웃 되었습니다.");
            response.sendRedirect("/");
        }
        // CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        // log.info("사용자 {} 가 로그아웃 합니다.", user.getUsername());
        // response.sendRedirect("/");        
    }
    
}
