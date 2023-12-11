package com.demo.testmysql2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
    
    @GetMapping(value={"", "/"})
    public String HelloServe() {
        return "Welcome";
    }

}
