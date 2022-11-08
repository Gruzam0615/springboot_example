package com.example.security.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.security.security.CustomUserDetails;
import com.example.security.user.entity.UserEntity;
import com.example.security.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired UserService userService;
    
    @GetMapping("/test01")
    @ResponseBody
    public String test01() {
        return "/test01";
    }

    @GetMapping("/")
    public String userIndex() {
        return "user/index";
    }

    @GetMapping("/signUp")
    public String signUpForm() {
        return "user/sign/signUp";
    }

    @PostMapping("/signUpProcess")
    @ResponseBody
    public String signUpProcess(Model model, UserEntity param, @RequestPart(value = "profileImageFile", required = false) MultipartFile profileImage) {
        UserEntity entity = new UserEntity();
        entity.setUserAccount(param.getUserAccount());
        entity.setUserPass(param.getUserPass());
        // log.info("[] :: {}", profileImage.getOriginalFilename());
        // entity.setProfileImage(param.getProfileImage().getOriginalFilename());
        // log.info("[] :: {}", param.getProfileImage().getOriginalFilename());
        UserEntity result = userService.save(entity);
        if(result != null) {
            return "true";
            // return "redirect:/user/sign/signIn";
        }
        else {
            // model.addAttribute("err", "회원가입에 실패했습니다.");
            // return "redirect:/user/signUp";
            return "false";
        }
        
    }

    @GetMapping("/signIn")
    public String signInForm() {
        return "user/sign/signIn";
    }

    @PostMapping("/signInSuccess")
    public String signInSuccess(
        @AuthenticationPrincipal CustomUserDetails user,
        RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute("userAccount", user.getUsername());
        return "redirect:/user/";
    }

    @PostMapping("/signInFailure")
    public String signInFailure(

    ) {
        return "redirect:user/";
    }

}
