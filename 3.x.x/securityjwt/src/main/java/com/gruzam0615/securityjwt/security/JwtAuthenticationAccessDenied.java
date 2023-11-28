package com.gruzam0615.securityjwt.security;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationAccessDenied implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
    AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // log.info("UNATHORIZED USER's request");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZE USER");
        // throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }

}
