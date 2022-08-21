package com.example.oauth2.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.oauth2.security.oauth2.OAuth2UserInfo;
import com.example.oauth2.user.entity.UserEntity;
import com.example.oauth2.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.oauth2.sdk.token.Token;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        
        // log.info("Principal에서 꺼낸 OAuth2User = {}", authentication.toString());
        // 최초 로그인이라면 회원가입 처리를 한다.
        // String targetUrl;
        // log.info("토큰 발행 시작");

        // Token token = tokenService.generateToken(userEntity.getUserEmail(), "USER");
        // log.info("{}", token);
        // targetUrl = UriComponentsBuilder.fromUriString("/home")
        //         .queryParam("token", "token")
        //         .build().toUriString();
        // getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}