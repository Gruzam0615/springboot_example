package com.gruzam0615.securityoauth2.security.oauth2.attribute;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.gruzam0615.securityoauth2.users.entity.Users;
import com.gruzam0615.securityoauth2.users.entity.UsersRole;
import com.gruzam0615.securityoauth2.users.service.UsersService;

public class GithubOAuth2Attribute extends UsersOauthAttribute {

    @Autowired
    private UsersService usersService;

    public GithubOAuth2Attribute(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getRegistrationId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getUsername() {
        return (String) attributes.get("name");
    }

    @Override
    public String getUsersEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getProfilePicture() {
        return (String) attributes.get("picture");
    }

    @Override
    public UsersRole getUsersRole() {
        Users u = usersService.findUsersByUsersAccount(getUsersEmail());
        return u.getUsersRole();
    }
    

}
