package com.gruzam0615.securitybasic.util.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.gruzam0615.securitybasic.users.entity.Users;
import com.gruzam0615.securitybasic.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private UsersService usersService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {
        String account = request.getParameter("account");
        Users u = usersService.findByUsersAccount(request.getParameter("account"));
        if(u != null) {
            log.info("user: {} password is Invalid or account Locked", account);
            usersService.increaseSignInFailureCount(request.getParameter("account"));
            response.sendRedirect("/sign/signInPage");
        }
        else {
            log.info("user: {} Not Exist", account);
            response.sendRedirect("/sign/signUpPage");
        }
        
    }
    
}