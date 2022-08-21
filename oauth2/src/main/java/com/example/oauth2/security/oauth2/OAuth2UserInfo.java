package com.example.oauth2.security.oauth2;

import java.util.Map;

import com.example.oauth2.user.entity.RoleType;

public abstract class OAuth2UserInfo {
    
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getUserAccount();

    public abstract String getUserName();

    public abstract String getUserEmail();

    public abstract String getUserProfileImage();

}
