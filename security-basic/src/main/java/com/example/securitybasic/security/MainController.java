package com.example.securitybasic.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class MainController {
    
    @GetMapping("/")
    public String getIndex() {
        return "user/index";
    }

}
