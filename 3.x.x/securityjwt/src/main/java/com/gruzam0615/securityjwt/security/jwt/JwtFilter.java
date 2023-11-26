package com.gruzam0615.securityjwt.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean{

    private final JwtTokenProvider jwtTokenProvider;

    private String checkToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(StringUtils.hasText(token)) {
            return token;
        }
        else {
            return null;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = checkToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        log.debug("input token:\n{}", token);
        log.debug("StringUtils.hasText() result: {}", StringUtils.hasText(token));
        log.debug("validate token result: {}", jwtTokenProvider.validateToken(token));

        // if(StringUtils.hasText(token) == true && jwtTokenProvider.validateToken(token) == true) {
        //     Authentication authentication = jwtTokenProvider.getAuthentication(token);
        //     SecurityContextHolder.getContext().setAuthentication(authentication);
        //     log.info("사용자 인증을 완료했습니다. 사용자명: {}", jwtTokenProvider.getUserName(token));
        // }
        // else if(StringUtils.hasText(token) == false) {
        //     log.info("token이 존재하지 않습니다.");
        // }
        // else {
        //     log.info("사용자 인증을 실패했습니다. 요청주소: {}", requestURI);
        // }
        chain.doFilter(request, response);
        // throw new UnsupportedOperationException("Unimplemented method 'doFilter'");
    }
    
}