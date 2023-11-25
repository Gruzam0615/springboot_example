package com.gruzam0615.securitybasic2.users.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gruzam0615.securitybasic2.users.entity.Users;
import com.gruzam0615.securitybasic2.util.CustomResponseEntity;

@RestController
@RequestMapping(value = "/api/sign")
public class UsersController {
    
    @GetMapping(value="/test01")
    public ResponseEntity<Object> test01() {
        // CustomResponseEntity result = new CustomResponseEntity("testing01 message", LocalDateTime.now());
        Users u = new Users();
        CustomResponseEntity result = new CustomResponseEntity("testing01 message", u, LocalDateTime.now());
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
    

}
