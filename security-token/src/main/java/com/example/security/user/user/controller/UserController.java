package com.example.security.user.user.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.security.security.CustomUserDetails;
import com.example.security.user.user.entity.UserEntity;
import com.example.security.user.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired 
    private UserService userService;
    
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
        RedirectAttributes redirectAttributes,
        @AuthenticationPrincipal CustomUserDetails user
    ) {
        log.info("사용자 {} 가 로그인했습니다.", user.getUsername());
        redirectAttributes.addAttribute("userAccount", user.getUsername());

        return "redirect:/";
    }
    
    @GetMapping("/afterSignInSuccess")
    public void afterSignInSuccess(
        @RequestParam(name = "userIdx") Long userIdx,
        @RequestParam(name = "accessToken") String accessToken,
        HttpServletRequest request,
        HttpServletResponse response
    )throws IOException  {
        RequestCache requestCache = new HttpSessionRequestCache();
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        int updateToken = userService.updateSignInToken(userIdx, accessToken);
        UserEntity user = userService.findBySignInToken(accessToken);      

        request.setAttribute("userAccount", user.getUserAccount());

        Cookie cookie = new Cookie("accessToken", accessToken);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        // cookie.setMaxAge(3600 * 24);
        cookie.setMaxAge(600);
        response.addCookie(cookie);
        redirectStrategy.sendRedirect(request, response, "/");
    }
    

    @PostMapping("/signInFailure")
    public String signInFailure(

    ) {
        return "redirect:user/";
    }

}
