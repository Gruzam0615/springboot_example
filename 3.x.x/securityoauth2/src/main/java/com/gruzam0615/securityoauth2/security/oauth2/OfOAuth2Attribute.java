package com.gruzam0615.securityoauth2.security.oauth2;

import java.util.Map;

import com.gruzam0615.securityoauth2.security.oauth2.attribute.GithubOAuth2Attribute;
import com.gruzam0615.securityoauth2.security.oauth2.attribute.GoogleOAuth2Attribute;
import com.gruzam0615.securityoauth2.security.oauth2.attribute.LocalOAuth2Attribute;
import com.gruzam0615.securityoauth2.security.oauth2.attribute.ProviderType;
import com.gruzam0615.securityoauth2.security.oauth2.attribute.UsersOauthAttribute;

public class OfOAuth2Attribute {
    public static UsersOauthAttribute getUsersOAuthInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch(providerType) {
            case GOOGLE: return new GoogleOAuth2Attribute(attributes);
            case GITHUB: return new GithubOAuth2Attribute(attributes);
            case LOCAL: return new LocalOAuth2Attribute(attributes);
            default: throw new IllegalArgumentException("Invalid SNS service: " + providerType.getProvider());
        }
    }
}
