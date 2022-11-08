package com.example.security.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.example.security.security.service.UserSignInSuccess;

@Configuration
// @Order(1)
public class CustomSecurityConfig {    

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors().disable()
            .antMatcher("/user/**")
            .authorizeRequests()
                .antMatchers("/user/signUp", "/user/signUpProcess", "/user/signIn").permitAll()
                .anyRequest().hasAuthority("CLIENT");

        http
            .formLogin()
                .loginPage("/user/signIn")
                    .loginProcessingUrl("/user/signInProcess")
                        .usernameParameter("userAccount").passwordParameter("userPass")
                        // .successForwardUrl("/user/signInSuccess")
                        .successHandler(new UserSignInSuccess())
                        .failureForwardUrl("/user/signInFailure")
            .and()
                .logout()
                    .logoutUrl("/user/signOut")
                        // .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/user/");
       
        return http.build();
    }

}
