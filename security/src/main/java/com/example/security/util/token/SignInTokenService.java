package com.example.security.util.token;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.Authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.security.security.CustomUserDetails;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class SignInTokenService {

    private String ISSUER = "SERVICE_DEVELOPER"; // Token 발급자
    private String SECRET_KEY = "SERVICE_SECRET_KEY"; // Token 발급 Secret_Key

    public Map<String, Object> createSignInToken(CustomUserDetails user) {
        Map<String, Object> result = new HashMap<>();
        String accessToken = "";
        String refreshToken = "";

        try {
            JWTCreator.Builder builder = JWT.create();
            builder.withSubject(String.valueOf(user.getUserIdx()));
            builder.withIssuer(ISSUER);
            builder.withClaim("userRole", user.getAuthorities().toArray()[0].toString());
            builder.withExpiresAt(expiresAt());
            accessToken += builder.sign(Algorithm.HMAC256(SECRET_KEY));
        }
        catch(JWTCreationException e) {
            log.error(e.getLocalizedMessage());
        }
        log.debug("[createSignInToken] 새로 생성한 AccessToken: {}", accessToken);

        result.put("accessToken", accessToken);
        result.put("refreshToekn", refreshToken);

        return result;
    }

    // Token 유효기간 설정
    private Date expiresAt() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());        
        // calendar.add(Calendar.HOUR, 744); // 1Month = 24 * 31
        calendar.add(Calendar.HOUR, 24); // 1Month = 24 * 31
        return calendar.getTime();
    }

    // Token에서 Claims를 추출 현재 Claims로 userIdx를 사용
    private Long getUsersIdxFromToken(String token) {
        DecodedJWT decodedJwt = JWT.decode(token);
        Claim claim = decodedJwt.getClaim("userIdx");
        Long result = claim.asLong();
        return result;
    }

    // Token 유효성 검사
    // public static boolean validateToken(String token) {
    //     try {
    //         JWT.decode(token).getToken();
    //     }
    // }

}
