package com.example.security.util.token;

import lombok.Data;

@Data
public class SignInToken {

    String token;
    String accessToken;
    String refreshToken;

    public SignInToken() {

    }

    public SignInToken(String token) {
        this.token = token;
    }

}
