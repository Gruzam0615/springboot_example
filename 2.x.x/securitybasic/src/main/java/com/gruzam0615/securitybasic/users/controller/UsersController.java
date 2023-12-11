package com.gruzam0615.securitybasic.users.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gruzam0615.securitybasic.config.security.CustomUserDetails;
import com.gruzam0615.securitybasic.users.entity.Users;
import com.gruzam0615.securitybasic.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@Slf4j
@Controller
@RequestMapping(value = "/sign")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/signUpPage")
    public String signUpPage() {
        return "sign/signUpPage";
    }

    @PostMapping(value = "/signUp")
    public String signUp(Users users) {
        // Thymeleaf에서 formData로 데이터를 전달받기 때문에 Users 엔티티를 통해 데이터를 전달 받을 수 있다.
        log.debug("users: {}", users.toString());
        Users u = usersService.save(users);
        if(u == null) {
            return "sign/signUpPage";
        }
        else {
            return "/index";
        }
    }

    @GetMapping
    @RequestMapping(value = "/signInPage")
    public String signInPage() {
        return "sign/signInPage";
    }

    @PostMapping(value="/signInSuccess")
    public String getMethodName(HttpServletRequest request, HttpServletResponse response, Model model) {
        // CustomUserDetails u = (CustomUserDetails) securityContext.getAuthentication().getPrincipal();
        // model.addAttribute("user", u);
        return "/index";
    }

    @GetMapping
    @RequestMapping(value = "/checkPassPage")
    public String checkPassPage(Model model) {
        CustomUserDetails ud = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", ud);
        return "sign/checkPassPage";
    }

    @PostMapping("/checkPass")
    public void checkPass(HttpServletResponse response, Users user) throws IOException {
        log.debug("input Data: {}", user.toString());
        boolean checkPassResult = usersService.checkPassword(user);
        if(checkPassResult == true) {
            response.sendRedirect("/sign/myPage");
        }
        else {
            // return "sign/checkPassPage";
            response.sendRedirect("/sign/checkPassPage");
        }
    }
    
    @GetMapping("/myPage")
    public String myPage(Model model) {
        log.info("called /sign/myPage");
        CustomUserDetails ud = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users u = usersService.findByUsersAccount(ud.getUsername());

        // Users user = usersService.findByUsersAccount(ud.getUsername());
        log.debug("ud: {}", u.toString());
        model.addAttribute("user", u);
        return "sign/myPage";
    }
    
    @PostMapping("/myPageUpdate")
    public void myPageUpdate(HttpServletResponse response, Users user) throws IOException {
        Users u = usersService.userUpdateMyPage(user);
        if(u != null) {
            log.info("User Information update complete");
            response.sendRedirect("/sign/myPage");
        }
        else {
            log.info("User Information update failed");
            response.sendRedirect("/sign/myPage");
        }
    }

    @PostMapping("/terminateAccount")
    public String terminateAccount(HttpServletRequest request, HttpServletResponse response, Users user) throws IOException {
        Users u = usersService.userTerminate(user);
        if(u == null) {
            return "sign/myPage";
        }
        else {
            HttpSession session = request.getSession();
            session.invalidate();
            SecurityContextHolder.clearContext();
            return "/index";
        }
    }
    
    
}
