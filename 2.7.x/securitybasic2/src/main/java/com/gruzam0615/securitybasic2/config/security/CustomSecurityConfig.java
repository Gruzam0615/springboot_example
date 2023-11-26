package com.gruzam0615.securitybasic2.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class CustomSecurityConfig {
    
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomFormLoginSuccess customFormLoginSuccess;
    private final CustomFormLoginFailure customFormLoginFailure;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http.cors((cors) -> cors.disable());

        http.sessionManagement((session) -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.authorizeRequests((req) -> req
            .antMatchers("/api/sign/**").permitAll()
            .anyRequest().authenticated()
        );

        http.httpBasic((basic) -> basic.disable());

        http.formLogin((form) -> form
            .loginPage("/")
            .loginProcessingUrl("/api/sign/signIn")
            .usernameParameter("account")
            .passwordParameter("password")
            .successHandler(customFormLoginSuccess)
            .failureHandler(customFormLoginFailure)
        );

        http.logout((logout) -> logout
            .logoutUrl("/api/sign/signOut")
            .logoutSuccessHandler(new LogoutSuccessHandler() {
                @Override
                public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                Authentication authentication) throws IOException, ServletException {
                    // log.info("signOut username: " + authentication.getName());
                    response.sendRedirect("/api/sign/signOutComplete");
                    // throw new UnsupportedOperationException("Unimplemented method 'onLogoutSuccess'");
                }
            })
            // .deleteCookies("삭제할쿠키명")
            .permitAll()
        );

        // http.apply(new JwtSecurityConfig(jwtTokenProvider));
        http.addFilterBefore(
            new JwtFilter(jwtTokenProvider),
            UsernamePasswordAuthenticationFilter.class
        );
        http.authenticationProvider(customAuthenticationProvider);

        return http.build();
    }

}
