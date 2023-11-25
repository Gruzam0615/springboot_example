package com.gruzam0615.securitybasic2.config.security;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gruzam0615.securitybasic2.users.entity.UsersRole;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secretkey}")
    private String secretKey;
    private int expireDate = 60 * 60 * 1000; // 1Hours

    private CustomUserDetailsService customUserDetailsService;

    // @PostConstruct
    // protected void init() {
    //     secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    // }

    private Date expiresAt() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, expireDate);
        return calendar.getTime();
    }

    public String createToken(String usersAccount, UsersRole usersRole) {
        return JWT.create()
            .withIssuer("securitybasic2")
            .withClaim("username", usersAccount)
            .withClaim("userRole", usersRole.getRole())
            .withExpiresAt(expiresAt())
            .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication getAuthentication(String token) {
        UserDetails u = customUserDetailsService.loadUserByUsername(token);
        return new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
    }
    
}
