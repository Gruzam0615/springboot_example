package com.gruzam0615.securityoauth2.users.entity;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UsersRole {

    CLIENT("client"),
    ADMIN("admin"),
    GUEST("guest");

    private final String role;

    public String getRole() {
        return role;
    }

    public static UsersRole of(String role) {
        return Arrays.stream(UsersRole.values())
            .filter(f -> f.getRole().equals(role))
            .findAny()
            .orElse(GUEST);
    }

    // public Object usersRoleAuthorityList(SimpleGrantedAuthority simpleGrantedAuthority) {
    //     // return of(simpleGrantedAuthority.getAuthority());
    //     return null;
    // }

}