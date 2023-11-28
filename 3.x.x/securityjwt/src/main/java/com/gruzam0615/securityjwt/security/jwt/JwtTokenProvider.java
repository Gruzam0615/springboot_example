package com.gruzam0615.securityjwt.security.jwt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gruzam0615.securityjwt.security.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${jwt.secretkey}")
    private String secretKey;
    @Value("${jwt.issuer}")
    private String issuer;
    private int expireDate = 60 * 60 * 1000; // 1Hours

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // @PostConstruct
    // protected void init() {
    //     secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    // }

    private Date expiresAt() {
        // Calendar calendar = Calendar.getInstance();
        // calendar.setTime(new Date());
        // calendar.add(Calendar.HOUR, expireDate);
        // return calendar.getTime();
        return new Date(System.currentTimeMillis() + expireDate);
    }

    public String createToken(String usersAccount, String usersRole) {
        return JWT.create()
            .withIssuer(issuer)
            .withClaim("username", usersAccount)
            .withClaim("userRole", usersRole)
            .withExpiresAt(expiresAt())
            .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication getAuthentication(String token) {
        UserDetails u = customUserDetailsService.loadUserByUsername(token);
        return new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
    }

    public boolean validateToken(String token) {
        DecodedJWT decodedJWT;
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(issuer)
            .build();
        decodedJWT = verifier.verify(token);
            
        log.debug("issuer: {}", decodedJWT.getIssuer());
        log.debug("payload: {}", decodedJWT.getPayload().toString());
        log.debug("exp: {}", decodedJWT.getExpiresAt());

        if(
            !decodedJWT.getExpiresAt().before(new Date(System.currentTimeMillis())) &&
            decodedJWT.getIssuer().equals(issuer) == true
        ) {
            log.info("Valid token");
            return true;
        }
        else if(decodedJWT.getExpiresAt().before(new Date(System.currentTimeMillis())) == false) {
            log.info("Expired token");
            return false;
        }
        else {
            log.info("Invalid token");
            return false;
        }
    }
    
    public String getUserName(String token) {
        return JWT.decode(token).getClaim("username").toString();
    }
}
