package com.example.security.admin.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.security.admin.admin.service.AdminService;
import com.example.security.security.CustomUserDetails;
import com.example.security.user.user.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired AdminService userService;
    
    @GetMapping("/test01")
    @ResponseBody
    public String test01() {
        return "/test01";
    }

    @GetMapping({"", "/"})
    public String userIndex() {
        return "admin/index";
    }

    @GetMapping("/signUp")
    public String signUpForm() {
        return "admin/sign/signUp";
    }

    @PostMapping("/signUpProcess")
    public String signUpProcess(UserEntity param) {
        userService.save(param);
        return "redirect:/admin/signIn";
    }

    @GetMapping("/signIn")
    public String signInForm() {
        return "admin/sign/signIn";
    }

    @PostMapping("/signInSuccess")
    public String signInSuccess(
        @AuthenticationPrincipal CustomUserDetails user,
        RedirectAttributes redirectAttributes
    ) {
        // if(user.getAuthorities().equals("ADMIN")) {
        //     redirectAttributes.addFlashAttribute("userAccount", user.getUsername());
        //     return "redirect:/admin/";
        // }
        // else {
        //     return "redirect:/admin/signIn";
        // }
        log.info(user.getAuthorities().toString());
        redirectAttributes.addFlashAttribute("userAccount", user.getUsername());
        return "redirect:/admin/";
    }

    @PostMapping("/signInFailure")
    public String signInFailure(

    ) {
        return "redirect:/admin";
    }

}
