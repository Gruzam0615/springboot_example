package com.gruzam0615.securitybasic.config.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private String usersAccount;
    private String usersPassword;
    private String usersRole;

    public CustomUserDetails(String usersAccount, String usersPassword, String usersRole) {
        this.usersAccount = usersAccount;
        this.usersPassword = usersPassword;
        this.usersRole = usersRole;
    }

    @Override
    public String toString() {
        return "username: " + usersAccount + " , " + "usersRole: " + usersRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(usersRole));
        return auth;
    }

    @Override
    public String getPassword() {
        return usersPassword;
    }

    @Override
    public String getUsername() {
        return usersAccount;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
