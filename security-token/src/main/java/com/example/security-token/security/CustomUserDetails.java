package com.example.security.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.security.user.user.entity.UserEntity;

public class CustomUserDetails implements UserDetails {

    private UserEntity user;

    public CustomUserDetails(UserEntity user) {
        this.user = user;
    }    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> auth = new ArrayList<>();
        String userRole = user.getUserRole();
        auth.add(new SimpleGrantedAuthority(userRole));
        return auth;
    }

    @Override
    public String getUsername() {
        return user.getUserAccount();
    }

    @Override
    public String getPassword() {
        return user.getUserPass();
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

    public Long getUserIdx() {
        return user.getUserIdx();
    }

    public String getSignInToken() {
        return user.getSignInToken();
    }

}
