package com.gruzam0615.securityoauth2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.gruzam0615.securityoauth2.security.oauth2.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class CustomSecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSignInFailureHandler customSignInFailureHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(c -> c.disable());
        http.csrf(c -> c.disable());

        http.authorizeHttpRequests((req) -> req
            .requestMatchers("/api/sign/**").permitAll()
            .requestMatchers("/test01").hasAuthority("client")
            .requestMatchers("/test2", "/getPrincipal").hasRole("ADMIN")
            .anyRequest().authenticated()
        );

        http.httpBasic(basic -> basic.disable());
        http.formLogin(form -> form.disable());

        http.oauth2Login(oauth2 -> oauth2
            .authorizationEndpoint(a -> a
                .baseUri("/oauth2/authorization")
            )
            .userInfoEndpoint(u -> u
                .userService(customOAuth2UserService)
            )
            .failureHandler(customSignInFailureHandler)
        );

        http.logout(logout -> logout
            .logoutSuccessUrl("/").permitAll()
        );

        http.exceptionHandling(e -> e
            .authenticationEntryPoint(customAuthenticationEntryPoint)
        );

        return http.build();

    }

}
