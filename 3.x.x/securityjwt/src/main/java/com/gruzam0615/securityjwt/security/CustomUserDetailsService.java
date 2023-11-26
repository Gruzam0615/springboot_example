package com.gruzam0615.securityjwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gruzam0615.securityjwt.users.entity.Users;
import com.gruzam0615.securityjwt.users.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users u = usersRepository.findUsersByUsersAccount(username);

        if(u == null) {
            throw new UsernameNotFoundException("user has not exist username: " + username);
        }
        else {
            log.debug("loadUserByUsername");
            log.debug("username: {}, userrole: {}", u.getUsersAccount(), u.getUsersRole());
            CustomUserDetails cu = new CustomUserDetails(
                u.getUsersAccount(),
                u.getUsersPassword(),
                u.getUsersRole().getRole(),
                u.isExpired(),
                u.isLocked(),
                u.isEnabled(),
                u.getSignInToken()
            );
            return cu;
        }
        
    }

    public UserDetails loadUserByToken(String token) throws UsernameNotFoundException {
        Users u = usersRepository.findUsersByToken(token);

        if(u == null) {
            throw new UsernameNotFoundException("user has not exist\ntoken: " + token);
        }
        else {
            log.debug("loadUserByToken");
            log.debug("username: {}, userrole: {}", u.getUsersAccount(), u.getUsersRole());
            CustomUserDetails cu = new CustomUserDetails(
                u.getUsersAccount(),
                u.getUsersPassword(),
                u.getUsersRole().getRole(),
                u.isExpired(),
                u.isLocked(),
                u.isEnabled()
            );
            return cu;
        }
        
    }

}
