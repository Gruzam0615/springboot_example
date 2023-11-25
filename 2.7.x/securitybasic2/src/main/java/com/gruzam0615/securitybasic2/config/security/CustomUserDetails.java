package com.gruzam0615.securitybasic2.config.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gruzam0615.securitybasic2.users.entity.UsersRole;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails {

    private String usersAccount;
    private String usersPassword;
    private String usersRole;
    private boolean expired;
    private boolean locked;
    private boolean enabled;
    private String signInToken;

    public CustomUserDetails(String usersAccount, String usersPassword, String usersRole, boolean expired, boolean locked, boolean enabled) {
        this.usersAccount = usersAccount;
        this.usersPassword = usersPassword;
        this.usersRole = usersRole;
        this.expired = expired;
        this.locked = locked;
        this.enabled = enabled;
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
