package com.example.securitybasic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.securitybasic.security.service.SignOutSuccess;

@Configuration
public class UserSecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf().disable()
            .cors().disable();

        http
            .authorizeRequests()
                .antMatchers("/", "/user/", "/user/signUp", "/user/signUpProcess", "/user/signIn").permitAll();
        http
            .authorizeRequests()
                .anyRequest().hasAuthority("USER");
                    // .anyRequest().permitAll();

        http
            .formLogin()
                .loginPage("/user/signIn")
                    .loginProcessingUrl("/user/signInProcess")
                        .usernameParameter("userAccount").passwordParameter("userPass")
                        .successForwardUrl("/user/signInSuccess")
                        .failureForwardUrl("/user/signInFailure")
            .and()
                .logout()
                    .logoutUrl("/user/signOut")
                        .deleteCookies("JSESSIONID")
                        // .logoutSuccessUrl("/user/signOutSuccess");
                        .logoutSuccessHandler(new SignOutSuccess());

        return http.build();

    }
    
}
