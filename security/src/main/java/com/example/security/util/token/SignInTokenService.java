package com.example.security.util.token;

import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class SignInTokenService {

    private String ISSUER = "SERVICE_DEVELOPER"; // Token 발급자
    private String SECRET_KEY = "SERVICE_SECRET_KEY"; // Token 발급 Secret_Key

    public String createSignInToken(Long usersIdx) {
        String result = "";
        try {
            JWTCreator.Builder builder = JWT.create();
            builder.withIssuer(ISSUER);
            builder.withClaim("usersIdx", usersIdx);
            builder.withExpiresAt(expiresAt());
            result = builder.sign(Algorithm.HMAC256(SECRET_KEY));
        }
        catch(JWTCreationException e) {
            log.error(e.getLocalizedMessage());
        }
        log.info("created Token: {}", result);
        return result;
    }

    private Date expiresAt() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // 1Month = 24 * 31
        calendar.add(Calendar.HOUR, 744);
        return calendar.getTime();
    }

}
