package com.gruzam0615.securityoauth2.security.oauth2;

import java.util.Collections;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.gruzam0615.securityoauth2.security.oauth2.attribute.ProviderType;
import com.gruzam0615.securityoauth2.security.oauth2.attribute.UsersOauthAttribute;
import com.gruzam0615.securityoauth2.users.entity.Users;
import com.gruzam0615.securityoauth2.users.entity.UsersRole;
import com.gruzam0615.securityoauth2.users.service.UsersService;

import jakarta.servlet.UnavailableException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private UsersService usersService;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        if(oAuth2User != null) {
            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
            // String userNameAttributeKey = oAuth2User.getAttribute("id").toString();
            
            // log.debug("oAuth2User\n{}", oAuth2User.toString());
            log.debug("registrationId: {}", registrationId); // providerName
            // log.debug("userNameAttributeName : Key = {} : {}", userNameAttributeName, userNameAttributeKey);

            UsersOauthAttribute attribute = OfOAuth2Attribute.getUsersOAuthInfo(registrationId, oAuth2User.getAttributes());
                        
            log.debug("attribute: {}", attribute.getAttributes().toString());
            log.debug("profile: {}", attribute.getProfilePicture());

            Users sessionUsers;
            try {
                sessionUsers = updateUsersInfo(registrationId, attribute);
            } catch (Exception e) {
                sessionUsers = null;
                log.error("OAuth2 Login Failure");
            }
            
            httpSession.setAttribute("user", sessionUsers);

            return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(sessionUsers.getUsersRole().getRole())), 
                attribute.getAttributes(),
                userNameAttributeName
            );
        }
        else {
            log.info("loadUser failed");
            // return new DefaultOAuth2User(
            //     Collections.singleton(new SimpleGrantedAuthority(UsersRole.GUEST.getRole())), 
            //     new HashMap<String, Object>(),
            //     null
            // );
            throw new UnsupportedOperationException("Unimplemented method 'loadUser'");
        }
        
    }

    private Users updateUsersInfo(String registrationId, UsersOauthAttribute attribute) throws Exception {
        Users u = usersService.findUsersByUsersEmail(attribute.getUsersEmail());
        if(u == null) {
            log.info("{} does not exist", attribute.getUsersEmail());
            return Users.builder()
                .usersEmail(attribute.getUsersEmail())
                .usersEmail(attribute.getProfilePicture())
                .usersRole(UsersRole.GUEST)
                .build();
        } else {
            if(u.getProvider().equals(ProviderType.LOCAL)) {
                log.info("OAuth2 connection, userInfo update complete");
                u.setProvider(ProviderType.of(registrationId));
                u.setProviderId(attribute.getRegistrationId());
                u.setProfilePicture(attribute.getProfilePicture());
                return usersService.updateUsersProfilePicture(u);
            }
            else if(u.getProvider().equals(ProviderType.of(registrationId))) {
                log.info("OAuth2 connection, userInfo load complete");
                u.setProvider(ProviderType.of(registrationId));
                u.setProviderId(attribute.getRegistrationId());
                u.setProfilePicture(attribute.getProfilePicture());
                return usersService.updateUsersProfilePicture(u);
            }
            else {
                log.info("OAuth2 connection, userInfo update failure.(Already Connected another ProviderType. {})", u.getProvider().getProvider());
                throw new UnavailableException("Already Connected another ProviderType LogIn Failure");
                // return Users.builder()
                //     .usersEmail(attribute.getUsersEmail())
                //     .usersEmail(attribute.getProfilePicture())
                //     .usersRole(UsersRole.GUEST)
                //     .build();
            }
           
        }
    }
    
}
