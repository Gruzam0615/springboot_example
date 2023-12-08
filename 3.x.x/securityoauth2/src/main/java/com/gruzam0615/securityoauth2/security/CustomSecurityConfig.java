package com.gruzam0615.securityoauth2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import com.gruzam0615.securityoauth2.security.oauth2.CustomOAuth2UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class CustomSecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSignInSuccessHandler customSignInSuccessHandler;
    private final CustomSignInFailureHandler customSignInFailureHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(c -> c.disable());
        http.csrf(c -> c.disable());

        http.authorizeHttpRequests((req) -> req
            // .requestMatchers("/api/sign/**").hasAnyRole("CLIENT", "ADMIN")
            .requestMatchers("/test01").hasAuthority("client")
            .anyRequest().authenticated()
        );

        http.httpBasic(basic -> basic.disable());
        http.formLogin(form -> form.disable());

        http.oauth2Login(oauth2 -> oauth2
            .authorizationEndpoint(a -> a
                // .baseUri("/oauth2/authorization")
                .baseUri("/login")
            )
            .userInfoEndpoint(u -> u
                .userService(customOAuth2UserService)
            )
            .successHandler(customSignInSuccessHandler)
            .failureHandler(customSignInFailureHandler)
        );

        http.logout(logout -> logout
            .logoutUrl("/logout")
            .addLogoutHandler((request, response, authentication) -> {
                HttpSession session = request.getSession();
                session.removeAttribute("user");
                session.invalidate();
            })
            // .addLogoutHandler(new LogoutHandler() {
            //     @Override
            //     public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
            //         httpSession.removeAttribute("user");
            //     }
                
            // })
            .clearAuthentication(true)
            .logoutSuccessUrl("/")
            .permitAll()
        );

        // // 23.12.04 Something Trouble occured, cause is unknown
        // // http.exceptionHandling(e -> e
        // //     .authenticationEntryPoint(customAuthenticationEntryPoint)
        // // );

        return http.build();

    }

}
