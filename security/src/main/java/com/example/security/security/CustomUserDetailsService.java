package com.example.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.user.User;
import com.example.security.user.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserAccount(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        // TODO Auto-generated method stub
        return new CustomUserDetails(user);
    }
    
}
