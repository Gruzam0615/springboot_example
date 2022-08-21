package com.example.oauth2.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.oauth2.user.entity.ProviderType;
import com.example.oauth2.user.entity.RoleType;
import com.example.oauth2.user.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomUserPrincipal implements OAuth2User, UserDetails, OidcUser {
    private final String userAccout;
    private final String userPass;
    private final ProviderType provider;
    private final RoleType roleType;
    private final Collection<GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return userAccout;
    }    

    @Override
    public String getUsername() {
        return userAccout;
    }

    @Override
    public String getPassword() {
        return userPass;
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

    @Override
    public Map<String, Object> getClaims() {
        return null;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return null;
    }

    @Override
    public OidcIdToken getIdToken() {
        return null;
    }

    public static CustomUserPrincipal create(UserEntity user) {
        return new CustomUserPrincipal(
                user.getUserAccount(),
                user.getUserPass(),
                user.getProvider(),
                user.getUserRole(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getUserRole().getCode()))
        );
    }

    public static CustomUserPrincipal create(UserEntity user, Map<String, Object> attributes) {
        CustomUserPrincipal userPrincipal = create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }


}