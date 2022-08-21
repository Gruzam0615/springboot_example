package com.example.oauth2.user.entity;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    CLIENT("CLIENT", "CLIENT"),
    DETECTIVE("DETECTIVE", "DETECTIVE"),
    ADMIN("ADMIN", "ADMIN"),
    SUPER_ADMIN("SUPER_ADMIN", "SUPER_ADMIN"),
    GUEST("GUEST", "GUEST");

    private final String code;
    private final String displayName;

    public static RoleType of(String code) {
        return Arrays.stream(RoleType.values())
                .filter(r -> r.getCode().equals(code))
                .findAny()
                .orElse(GUEST);
    }

    public Object singletonList(SimpleGrantedAuthority simpleGrantedAuthority) {
        return null;
    }
}
