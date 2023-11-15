package com.gruzam0615.securitybasic.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/sign")
public class UsersController {
    
    @GetMapping
    @RequestMapping(value = "/signInPage")
    public String signInPage() {
        return "sign/signInPage";
    }

}
