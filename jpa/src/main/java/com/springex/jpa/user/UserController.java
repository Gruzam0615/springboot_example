package com.springex.jpa.user;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springex.jpa.util.random.RandomGenAccount;

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
    @ResponseBody
    public void signUpUser(
        UserEntity user,
        HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        // log.info("registerUser {}", user.toString());
        userService.save(user);
        response.sendRedirect("/");
    }

    @GetMapping("/randomSignUpUser")
    @ResponseBody
    public String randomSignUpUser(HttpServletResponse response) {
        Set<String> randomUserAccountSet = new HashSet<>();
        String[] userRole = {"USER", "MANAGER"};
        while(randomUserAccountSet.size() < 100) {
            randomUserAccountSet.add(new RandomGenAccount().generateAccount());
        }
        randomUserAccountSet.forEach(item -> {
            UserEntity user = new UserEntity();
            user.setUserAccount(item);
            user.setUserPass(item);
            user.setUserName(item);
            user.setUserRole(userRole[(int) Math.floor(Math.random() * 2)]);
            log.info("new temp User: {}", user.toString());
            userService.save(user);
        });
        return "DONE";
    } 

}
