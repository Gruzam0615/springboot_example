package com.example.oauth2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.oauth2.security.oauth2.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CustomSecurityConfig {

    private final CustomOAuth2SuccessHandler successHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.httpBasic((basic) -> basic
            .disable()
        );            
            
        http.csrf((c) -> c
            // H2를 사용을 위한 설정
            .requireCsrfProtectionMatcher(new AntPathRequestMatcher("!/h2-console/**")) 
        );

        http.headers((h) -> h
            .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'")).frameOptions().disable()         // H2를 사용하는경우 end
        );

        http.cors((c) -> c
            .disable());

        http.formLogin((form) -> form
            .disable());
            
        http.authorizeRequests((req) -> req
            // .antMatcher("/api/**")            
            .antMatchers("/", "/api/users/**").permitAll()
        //     .anyRequest().authenticated()
        );

        http.oauth2Login((o) -> o
            .authorizationEndpoint()
            .baseUri("/oauth2/authorization")
            .and()
            .successHandler(successHandler)
            .userInfoEndpoint()
            .userService(customOAuth2UserService)
        );

        return http.build();

    }

}
