package com.gruzam0615.securityoauth2.users.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gruzam0615.securityoauth2.users.entity.Users;
import com.gruzam0615.securityoauth2.users.service.UsersService;
import com.gruzam0615.securityoauth2.util.CustomResponseEntity;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping(value="/api/sign")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping(value="/test01")
    public ResponseEntity<Object> test01() {
        log.info("/api/sign/test01 respone...");
        // CustomResponseEntity result = new CustomResponseEntity("testing01 message", LocalDateTime.now());
        Users u = new Users();
        CustomResponseEntity result = new CustomResponseEntity("/api/sign/testing01", u, LocalDateTime.now());
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    @GetMapping(value="/test02")
    public ResponseEntity<Object> test02(@RequestBody Users param) {
        log.info("/api/sign/test02 Requested...");
        // CustomResponseEntity result = new CustomResponseEntity("testing01 message", LocalDateTime.now());
        Users u = usersService.findUsersByUsersAccount(param.getUsersAccount());
        CustomResponseEntity result = new CustomResponseEntity("/api/sign/testing01", u, LocalDateTime.now());
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    /**
     * 로그인 메서드 Frontend의 Request 수행 시 데이터를 FormData로 전달 받음
     * @return ResponseEntity<Object>
     */
    // @PostMapping(value="/signIn")
    // public ResponseEntity<Object> signInMethod(Users user) {
    //     String account = user.getUsersAccount();
    //     String password = user.getUsersPassword();
    //     log.info("account: {}", account);
    //     return new ResponseEntity<>("/api/sign/signIn", null, HttpStatus.OK);
    // }

    /**
     * 로그인 메서드 Frontend의 Request 수행 시 데이터를 Raw json으로 전달 받음
     * @return ResponseEntity<Object>
     */
    // @PostMapping(value="/signIn")
    // public ResponseEntity<Object> signInMethod(@RequestBody Users user) {
    //     CustomResponseEntity result = new CustomResponseEntity();
    //     String usersAccount = user.getUsersAccount();
    //     log.info("Request Sign In username: {}", usersAccount);
    //     String newToken = usersService.signIn(user);

    //     if(newToken != null) {
    //         result.setMessage("/api/sign/signIn SUCCESS");
    //         result.setData(newToken);
    //         result.setTimeStamp(LocalDateTime.now());
    //     }
    //     else {
    //         result.setMessage("/api/sign/signIn FAILURE");
    //         result.setData(null);
    //         result.setTimeStamp(LocalDateTime.now());
    //     }

    //     return new ResponseEntity<>(result, null, HttpStatus.OK);
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
