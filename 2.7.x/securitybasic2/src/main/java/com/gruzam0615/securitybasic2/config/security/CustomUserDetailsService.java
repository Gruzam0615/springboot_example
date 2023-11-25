package com.gruzam0615.securitybasic2.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gruzam0615.securitybasic2.users.entity.Users;
import com.gruzam0615.securitybasic2.users.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        Users u = usersRepository.findUsersByToken(token);

        if(u == null) {
            throw new UsernameNotFoundException("username: " + u.getUsersAccount() + " does not exist");
        }
        else {
            log.debug("u: " + u.toString());
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
