package com.gruzam0615.securitybasic.util.handler;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.gruzam0615.securitybasic.config.security.CustomUserDetails;
import com.gruzam0615.securitybasic.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UsersService usersService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails u = (CustomUserDetails) authentication.getPrincipal();
        
        if((u != null && u.isAccountNonLocked() == true)) {
            log.info("user: {} sign In timestamp: {}", u.getUsername(), LocalDateTime.now());
            usersService.initSignInFailureCount(u.getUsername());
            response.sendRedirect("/");
        }
        else {
            log.info("user: {} not exist", u.getUsername());
            response.sendRedirect("/sign/signInPage");
        }

    }

}
