package com.gruzam0615.securityoauth2.security.oauth2.attribute;

import java.util.Map;

import com.gruzam0615.securityoauth2.users.entity.UsersRole;

public abstract class UsersOauthAttribute {
    
    protected Map<String, Object> attributes;

    public UsersOauthAttribute(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getRegistrationId();
    public abstract String getUsername();
    public abstract String getUsersEmail();
    public abstract String getProfilePicture();
    public abstract UsersRole getUsersRole();

}
