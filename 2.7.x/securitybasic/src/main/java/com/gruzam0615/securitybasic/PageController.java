package com.gruzam0615.securitybasic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    
    @GetMapping
    @RequestMapping(value = "/")
    public String index() {
        return "/index";
    }

}
