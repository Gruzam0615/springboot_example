package com.gruzam0615.securitybasic.config.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private String usersAccount;
    private String usersPassword;
    private String usersEmail;
    private String usersRole;
    private boolean expired;
    private boolean locked;
    private boolean enabled;

    // public CustomUserDetails(String usersAccount, String usersPassword, String usersRole) {
    //     this.usersAccount = usersAccount;
    //     this.usersPassword = usersPassword;
    //     this.usersRole = usersRole;
    // }
    public CustomUserDetails(String usersAccount, String usersPassword, String usersEmail, String usersRole, boolean expired, boolean locked, boolean enabled) {
        this.usersAccount = usersAccount;
        this.usersPassword = usersPassword;
        this.usersEmail = usersEmail;
        this.usersRole = usersRole;
        this.expired = expired;
        this.locked = locked;
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "username: " + usersAccount + " " + "usersRole: " + usersRole + " " + "usersEmail: " + usersEmail;
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

    public String getUsersEmail() {
        return usersEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
}
