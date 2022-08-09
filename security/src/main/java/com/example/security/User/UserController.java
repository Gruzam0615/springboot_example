package com.example.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.security.security.CustomUserDetails;

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
    public String signUpProcess(User param) {
        userService.save(param);
        return "redirect:/user/signIn";
    }

    @GetMapping("/signIn")
    public String signInForm() {
        return "user/sign/signIn";
    }

    @PostMapping("/signInSuccess")
    public ModelAndView signInSuccess(
        @AuthenticationPrincipal CustomUserDetails user
    ) {
        ModelAndView mv = new ModelAndView();

        mv.addObject("userAccount", user.getUsername());
        mv.setViewName("user/index");

        return mv;
    }

    @PostMapping("/signInFailure")
    public ModelAndView signInFailure(

    ) {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("user/index");

        return mv;
    }

}
