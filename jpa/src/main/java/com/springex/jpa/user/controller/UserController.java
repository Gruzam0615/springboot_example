package com.springex.jpa.user.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springex.jpa.user.entity.UserEntity;
import com.springex.jpa.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping("/signUp")
    public String signUp() {
        return "user/signUp";
    }    

    @PostMapping("/signUpUser")
    public void signUpUser(
        UserEntity user,
        @RequestPart(name="userProfile", required = false) MultipartFile file1,
        @RequestPart(name="userFiles", required = false) List<MultipartFile> file2,
        HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        log.info("registerUser: {}", user.toString());
        // log.info("file1: {}", file1.getOriginalFilename());
        // log.info("file2: {}", file2.toString());
        
        UserEntity insertNewUser = userService.save(user);
        if(insertNewUser != null) {
            response.sendRedirect("/");
        }
        else {
            response.sendRedirect("/user/signUpUser");
        }
        
    }

    @GetMapping("/selectUserInfoByUserAccount")
    @ResponseBody
    public String selectUserInfoByUserAccount(
        @RequestParam("userAccount") String userAccount
    ) {
        // UserEntity userInfo = userService.selectUserInfoByUserAccount(userAccount);
        // return userInfo.toString();
        return null;
    }
}
