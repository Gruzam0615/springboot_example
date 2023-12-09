package com.gruzam0615.securitybasic.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.gruzam0615.securitybasic.util.handler.CustomAuthenticationFailureHandler;
import com.gruzam0615.securitybasic.util.handler.CustomAuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests(requests -> requests
            .antMatchers("/", "/index", "/sign/**").permitAll()
            .anyRequest().authenticated()
        );

        http.formLogin((form) -> form
            .loginPage("/sign/signInPage")
            .loginProcessingUrl("/sign/signIn")
            .usernameParameter("account")
            .passwordParameter("password")
            .successHandler(customAuthenticationSuccessHandler)
            .failureHandler(customAuthenticationFailureHandler)
            .permitAll()
        );

        http.logout((logout) -> logout
            .logoutUrl("/sign/signOut")
            .logoutSuccessHandler(new LogoutSuccessHandler() {
                @Override
                public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                throws IOException, ServletException {
                    log.info("signOut username: " + authentication.getName());
                    response.sendRedirect("/");
                }
            })
            // .deleteCookies("삭제할쿠키명")
            .permitAll()
        );

        return http.build();
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user = User.withDefaultPasswordEncoder()
    //         .username("user")
    //         .password("1234")
    //         .roles("CLIENT")
    //         .build();

    //     return new InMemoryUserDetailsManager(user);
    // }

}
