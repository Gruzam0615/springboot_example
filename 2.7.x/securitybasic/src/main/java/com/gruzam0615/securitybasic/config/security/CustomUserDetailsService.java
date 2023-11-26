package com.gruzam0615.securitybasic.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gruzam0615.securitybasic.users.entity.Users;
import com.gruzam0615.securitybasic.users.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users u = usersRepository.findByUsersAccount(username);

        if(u == null) {
            throw new UsernameNotFoundException("username: " + username + " does not exist");
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
