package com.example.security.security.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.security.security.service.JwtAuthenticationEntryPoint;
import com.example.security.security.service.SignOutSuccess;
import com.example.security.security.service.UserSignInFailure;
import com.example.security.security.service.UserSignInSuccess;

@Configuration
// @Order(1)
public class CustomSecurityConfig {    

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }   

    @Bean
    public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors().disable()
            .exceptionHandling();

        // http
            // .addFilter(new CorsConfig().corsFilter())
            // .csrf().disable()
            // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http
            .authorizeRequests()
                .antMatchers("/", "/user/signUp", "/user/signUpProcess", "/user/signIn").permitAll()
                .anyRequest().authenticated();
                // .anyRequest().permitAll();


        http
            .formLogin()
                .loginPage("/user/signIn")
                    .loginProcessingUrl("/user/signInProcess")
                        .usernameParameter("userAccount").passwordParameter("userPass")
                        .successHandler(new UserSignInSuccess())
                        .failureHandler(new UserSignInFailure())
            .and()
                .logout()
                    .logoutUrl("/user/signOut")
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler(new SignOutSuccess());
                        
       
        return http.build();
    }

}
