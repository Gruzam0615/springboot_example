package com.gruzam0615.securitybasic.config.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gruzam0615.securitybasic.users.entity.Users;
import com.gruzam0615.securitybasic.users.repository.UsersRepository;

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
            CustomUserDetails cu = new CustomUserDetails(u.getUsersAccount(), u.getUsersPassword(), u.getUsersRole().getRole());
            return cu;
        }
        
    }
    
}
