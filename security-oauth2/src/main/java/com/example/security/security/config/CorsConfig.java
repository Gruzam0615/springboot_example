package com.example.security.security.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); 
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        // config.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));

        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }
    
}
