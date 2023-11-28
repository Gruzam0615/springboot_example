package com.gruzam0615.securityjwt.security;

import java.io.IOException;

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

import com.gruzam0615.securityjwt.security.jwt.JwtAuthenticationEntryPoint;
import com.gruzam0615.securityjwt.security.jwt.JwtFilter;
import com.gruzam0615.securityjwt.security.jwt.JwtTokenProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class CustomSecurityConfig {
   
    private final JwtTokenProvider jwtTokenProvider;

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

        http.authorizeHttpRequests((req) -> req
            .requestMatchers("/api/sign/**").permitAll()
            .requestMatchers("/test01").hasAuthority("client")
            .requestMatchers("/test2", "/getPrincipal").hasRole("ADMIN")
            .anyRequest().authenticated()
        );

        http.httpBasic((basic) -> basic.disable());
        http.formLogin((form) -> form.disable());

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
            // .deleteCookies("JSESSIONID")
            // .deleteCookies("삭제할쿠키명")
            .permitAll()
        );

        // SecurityConfigurerAdapter Class를 거치는 방식
        // http.apply(new JwtSecurityConfig(jwtTokenProvider));

        http.addFilterBefore(
            new JwtFilter(jwtTokenProvider),
            UsernamePasswordAuthenticationFilter.class
        );

        http.exceptionHandling((exception) -> exception
            .accessDeniedHandler(new JwtAuthenticationAccessDenied())
            .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
        );
          
        

        return http.build();
    }

}
