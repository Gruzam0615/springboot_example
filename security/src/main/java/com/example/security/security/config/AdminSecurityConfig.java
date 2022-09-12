package com.example.security.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;


@Configuration
@Order(1)
public class AdminSecurityConfig {
    
    @Bean
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors().disable()
            .antMatcher("/admin/**")
            .authorizeRequests()
                .antMatchers("/admin/signUp", "/admin/signUpProcess", "/admin/signIn").permitAll()
                .anyRequest().hasAnyAuthority("ADMIN");

        http
            .formLogin()
                .loginPage("/admin/signIn")
                    .loginProcessingUrl("/admin/signInProcess")
                        .usernameParameter("userAccount").passwordParameter("userPass")
                        .successForwardUrl("/admin/signInSuccess")
                        .failureForwardUrl("/admin/signInFailure")
            .and()
                .logout()
                    .logoutUrl("/admin/signOut")
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/admin/");
        
        return http.build();
    }

}
