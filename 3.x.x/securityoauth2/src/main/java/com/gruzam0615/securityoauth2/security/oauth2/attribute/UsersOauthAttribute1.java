package com.gruzam0615.securityoauth2.security.oauth2.attribute;

import java.util.Map;

import com.gruzam0615.securityoauth2.users.entity.Users;
import com.gruzam0615.securityoauth2.users.entity.UsersRole;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UsersOauthAttribute1 {
    
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String userName;
    private String email;
    private String profilePicture;

    @Builder
    public UsersOauthAttribute1(Map<String, Object> attributes, String nameAttributeKey, String userName, String email, String profilePicture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.userName = userName;
        this.email = email;
        this.profilePicture = profilePicture;
    }

    public static UsersOauthAttribute1 of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return null;
    }

    private static UsersOauthAttribute1 ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return UsersOauthAttribute1.builder()
            .userName((String) attributes.get("name"))
            .email((String) attributes.get("email"))
            .profilePicture((String) attributes.get("picture"))
            .attributes(attributes)
            .nameAttributeKey(userNameAttributeName)
            .build();
    }

    public Users toEntity() {
        return Users.builder()
            .usersAccount(userName)
            .usersEmail(email)
            .profilePicture(profilePicture)
            .usersRole(UsersRole.GUEST)
            .build();
    }

}
