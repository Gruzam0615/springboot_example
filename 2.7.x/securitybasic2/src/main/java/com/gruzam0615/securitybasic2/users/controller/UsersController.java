package com.gruzam0615.securitybasic2.users.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gruzam0615.securitybasic2.users.entity.Users;
import com.gruzam0615.securitybasic2.util.CustomResponseEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/sign")
public class UsersController {
    
    @GetMapping(value="/test01")
    public ResponseEntity<Object> test01() {
        log.info("/api/sign/test01 respone...");
        // CustomResponseEntity result = new CustomResponseEntity("testing01 message", LocalDateTime.now());
        Users u = new Users();
        CustomResponseEntity result = new CustomResponseEntity("/api/sign/testing01", u, LocalDateTime.now());
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    // @PostMapping(value="/signIn")
    // public ResponseEntity<Object> signInMethod(Users user) {
    //     String account = user.getUsersAccount();
    //     String password = user.getUsersPassword();
    //     log.info("account: {}", account);
    //     return new ResponseEntity<>("/api/sign/signIn", null, HttpStatus.OK);
    // }

    @GetMapping(value="/signInSuccess")
    public ResponseEntity<Object> signInSuccess() {
        log.info("signIn Success");
        return new ResponseEntity<>("/api/sign/signInSuccess", null, HttpStatus.OK);
    }

    @GetMapping(value="/signInFailure")
    public ResponseEntity<Object> signInFailure() {
        log.info("signIn Failure");
        return new ResponseEntity<>("/api/sign/signInFailure", null, HttpStatus.OK);
    }
    
    @GetMapping(value="/signOutComplete")
    public ResponseEntity<Object> signOutComplete() {
        log.info("signOut Complete");
        return new ResponseEntity<>("/api/sign/signOutComplete", null, HttpStatus.OK);
    }

}
