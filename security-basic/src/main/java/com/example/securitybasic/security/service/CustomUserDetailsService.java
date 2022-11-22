package com.example.securitybasic.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.securitybasic.security.CustomUserDetails;
import com.example.securitybasic.user.entity.UserEntity;
import com.example.securitybasic.user.service.UserService;


@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired 
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findByUserAccount(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }    
}
