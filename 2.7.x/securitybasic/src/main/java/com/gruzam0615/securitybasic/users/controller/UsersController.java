package com.gruzam0615.securitybasic.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gruzam0615.securitybasic.users.entity.Users;
import com.gruzam0615.securitybasic.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/sign")
public class UsersController {

    @Autowired
    private UsersService usersService;
    
    @GetMapping
    @RequestMapping(value = "/signInPage")
    public String signInPage() {
        return "sign/signInPage";
    }

    @GetMapping(value = "/signUpPage")
    public String signUpPage() {
        return "sign/signUpPage";
    }

    @PostMapping(value = "/signUp")
    public String signUp(Users users) {
        log.debug("users: {}", users.toString());
        Users u = usersService.save(users);
        if(u == null) {
            return "sign/signUpPage";
        }
        else {
            return "/index";
        }
    }
    

}
