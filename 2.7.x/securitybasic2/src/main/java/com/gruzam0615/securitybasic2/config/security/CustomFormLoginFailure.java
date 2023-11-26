package com.gruzam0615.securitybasic2.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.gruzam0615.securitybasic2.users.entity.Users;
import com.gruzam0615.securitybasic2.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomFormLoginFailure implements AuthenticationFailureHandler {

    @Autowired
    private UsersService usersService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {
        String account = request.getParameter("account");
        Users u = usersService.findUsersByUsersAccount(request.getParameter("account"));
        if(u != null) {
            log.info("username: {} password is Invalid or account Locked", account);
            // usersService.increaseSignInFailureCount(request.getParameter("account"));
            response.sendRedirect("/api/sign/signInFailure");
        }
        else {
            log.info("username: {} Not Exist", account);
            // response.sendRedirect("/sign/signUpPage");
            response.sendRedirect("/api/sign/signInFailure");
        }
        
    }
    
}
