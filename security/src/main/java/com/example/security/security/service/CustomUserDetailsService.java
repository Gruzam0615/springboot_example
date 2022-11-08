package com.example.security.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.security.CustomUserDetails;
import com.example.security.user.entity.UserEntity;
import com.example.security.user.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findByUserAccount(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }    
}
