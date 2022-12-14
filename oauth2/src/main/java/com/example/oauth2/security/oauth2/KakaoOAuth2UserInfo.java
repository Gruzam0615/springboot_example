package com.example.oauth2.security.oauth2;

import java.util.Map;

public class KakaoOAuth2UserInfo  extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getUserAccount() {
        return attributes.get("id").toString();
    }

    @Override
    public String getUserName() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        if (properties == null) {
            return null;
        }
        return (String) properties.get("nickname");
    }

    @Override
    public String getUserEmail() {
        return (String) attributes.get("account_email");
    }

    @Override
    public String getUserProfileImage() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        if (properties == null) {
            return null;
        }
        return (String) properties.get("thumbnail_image");
    }
}