package com.example.oauth2.security.token;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class AuthToken {
    
    private final String token;
    private final Key key;

    private static final String AUTHORITIES_KEY = "usersRole";

    AuthToken(String id, Date expiry, Key key) {
        this.key = key;
        this.token = createAuthToken(id, expiry);
    }

    AuthToken(String id, String usersRole, Date expiry, Key key) {
        this.key = key;
        this.token = createAuthToken(id, usersRole, expiry);
    }

    AuthToken(String token, Key key) {
        this.token = token;
        this.key = key;
    }

    private String createAuthToken(String id, Date expiry) {
        return Jwts.builder()
            .setSubject(id)
            .signWith(key, SignatureAlgorithm.HS256)
            .setExpiration(expiry)
            .compact();
    }

    private String createAuthToken(String id, String usersRole, Date expiry) {
        return Jwts.builder()
            .setSubject(id)
            .claim(AUTHORITIES_KEY, usersRole)
            .signWith(key, SignatureAlgorithm.HS256)
            .setExpiration(expiry)
            .compact();
    }

    public boolean validate() {
        return this.getTokenClaims() != null;
    }

    public Claims getTokenClaims() {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        }
        catch(SecurityException e) {
            log.error("Invalid JWT signature");
        }
        catch(MalformedJwtException e) {
            log.error("Invalid JWT Token");
        }
        catch(ExpiredJwtException e) {
            log.error("Expired JWT Token");
        }
        catch(UnsupportedJwtException e) {
            log.error("Unsupported JWT Token");
        }
        catch(IllegalArgumentException e) {
            log.error("JWT Token compact of handler are invalid");
        }
        return null;
    }

    public Claims getExpiredTokenClaims() {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build().parseClaimsJws(token)
                .getBody();
        }
        catch(ExpiredJwtException e) {
            log.error("Expired JWT Token");
            return e.getClaims();
        }
        return null;
    }

}
