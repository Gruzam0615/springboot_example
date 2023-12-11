package com.gruzam0615.securitybasic.users.entity;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProviderType {

    GOOGLE("google"),
    GITHUB("github"),
    LOCAL("local");

    private final String provider;
    
    public String getProvider() {
        return provider;
    }

    public static ProviderType of(String provider) {
        return Arrays.stream(ProviderType.values())
            .filter(f -> f.getProvider().equals(provider))
            .findAny()
            .orElse(LOCAL);
    }
}
