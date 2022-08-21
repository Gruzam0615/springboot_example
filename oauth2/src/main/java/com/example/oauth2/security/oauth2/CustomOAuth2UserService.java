package com.example.oauth2.security.oauth2;

import java.time.LocalDateTime;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.oauth2.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        try {
            user.toString();
            return user;
        }
        catch(AuthenticationException e) {
            throw e;
        }
        catch(Exception e) {
            e.printStackTrace();
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());

        }
        // try {
        //     return this.process(userRequest, user);
        // } catch (AuthenticationException ex) {
        //     throw ex;
        // } catch (Exception ex) {
        //     ex.printStackTrace();
        //     throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        // }
    }

    // private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
    //     ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

    //     OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());
    //     UserEntity savedUser = userRepository.findByUserAccount(userInfo.getUserAccount());

    //     if (savedUser != null) {
    //         if (providerType != savedUser.getProvider()) {
    //             throw new OAuthProviderMissMatchException(
    //                     "Looks like you're signed up with " + providerType +
    //                     " account. Please use your " + savedUser.getProvider() + " account to login."
    //             );
    //         }
    //         updateUser(savedUser, userInfo);
    //     } else {
    //         savedUser = createUser(userInfo, providerType);
    //     }

    //     return CustomUserPrincipal.create(savedUser, user.getAttributes());
    // }

    // private UserEntity createUser(OAuth2UserInfo userInfo, ProviderType providerType) {
    //     LocalDateTime now = LocalDateTime.now();
    //     UserEntity user = new UserEntity(
    //             userInfo.getUserAccount(),
    //             userInfo.getUserName(),
    //             userInfo.getUserEmail(),
    //             // "Y",
    //             RoleType.CLIENT,
    //             userInfo.getUserProfileImage()
    //             // now,
    //             // now
    //     );

    //     return userRepository.saveAndFlush(user);
    // }

    // private UserEntity updateUser(UserEntity user, OAuth2UserInfo userInfo) {
    //     if (userInfo.getUserName() != null && !user.getUserName().equals(userInfo.getUserName())) {
    //         user.setUserName(userInfo.getUserName());
    //     }

    //     if (userInfo.getUserProfileImage() != null && !user.getUserProfileImg().equals(userInfo.getUserProfileImage())) {
    //         user.setUserProfileImg(userInfo.getUserProfileImage());
    //     }

    //     return user;
    // }
}
