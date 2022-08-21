package com.example.oauth2.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @GetMapping("/OAuth2Success")
    public String test01() {
        return "success";
    }

    @GetMapping("/OAuth2Failure")
    public String test02() {
        return "failure";
    }

}
