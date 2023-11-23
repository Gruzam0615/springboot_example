package com.gruzam0615.securitybasic2.users.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/api/sign")
public class UsersController {
    
    @GetMapping(value="/test01")
    public ResponseEntity<Object> test01(@RequestParam String param) {
        return new ResponseEntity<>("/test01 Done", null, HttpStatus.OK);
    }
    

}
