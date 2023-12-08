package com.gruzam0615.securityoauth2.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSignInFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
    AuthenticationException exception) throws IOException, ServletException {
        request.getSession().setAttribute("error", exception.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
        response.sendRedirect("/api/sign/signInFailure");
        // throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
    }

}
