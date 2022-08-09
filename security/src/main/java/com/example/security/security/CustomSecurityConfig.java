package com.example.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomSecurityConfig {
    
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors().disable()
            .authorizeRequests()
                .antMatchers("/user/signUp", "/user/signUpProcess", "/user/signIn").permitAll()
                .anyRequest().authenticated();
                // .anyRequest().permitAll();

        http
            .formLogin()
                .loginPage("/user/signIn")
                    .loginProcessingUrl("/user/signInProcess")
                        .usernameParameter("userAccount").passwordParameter("userPass")
                        .successForwardUrl("/user/signInSuccess")
                        .failureForwardUrl("/user/signInFailure");
        
        return http.build();
    }

}
