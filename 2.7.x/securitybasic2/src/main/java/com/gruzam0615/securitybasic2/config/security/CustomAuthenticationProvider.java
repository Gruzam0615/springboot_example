package com.gruzam0615.securitybasic2.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gruzam0615.securitybasic2.users.entity.Users;
import com.gruzam0615.securitybasic2.users.service.UsersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UsersService usersService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomUserDetails u = (CustomUserDetails) customUserDetailsService.loadUserByUsername(authentication.getName());
        String pass = authentication.getCredentials().toString();

        if(!passwordEncoder.matches(pass, u.getPassword())) {
            log.info("password incorrect username: {}", authentication.getName());
            throw new BadCredentialsException("password incorrect username: " + authentication.getName());
        }
        else {
            log.info("password correct");
            if(u.getSignInToken() == null) {
                String newToken = jwtTokenProvider.createToken(u.getUsersAccount(), u.getUsersRole());
                Users saveToken = usersService.updateUsersToken(u.getUsersAccount(), newToken);
                if(newToken == null) {
                    log.info("Faied create token");
                }
                else if(saveToken == null) {
                    log.info("Failed save token");
                }
                else {
                    log.info("Completed create&save token");
                }
            }
            return new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
