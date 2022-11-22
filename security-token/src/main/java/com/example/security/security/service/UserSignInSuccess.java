package com.example.security.security.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.example.security.security.CustomUserDetails;
import com.example.security.util.token.SignInTokenService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserSignInSuccess implements AuthenticationSuccessHandler {

    private SignInTokenService signInTokenService;

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, 
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException, ServletException {
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        log.info("[onAuthenticationSuccess] 사용자ID {} 가 로그인 합니다.", user.getUsername());
        // log.info("[onAuthenticationSuccess] 사용자ID {} 의 userRole: {}", user.getUsername(), user.getAuthorities().toArray()[0]);

        Map<String, Object> token = signInTokenService.createSignInToken(user);
        // log.info("사용자ID {} 의 새로 생성된 token: {}", user.getUsername(), token.get("accessToken"));

        response.sendRedirect("/user/afterSignInSuccess?userIdx=" + user.getUserIdx() + "&accessToken=" + token.get("accessToken").toString());
    }
        
}
