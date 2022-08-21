package com.example.oauth2.security.oauth2;

import java.util.Map;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getUserAccount() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getUserName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getUserEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getUserProfileImage() {
        return (String) attributes.get("picture");
    }
}