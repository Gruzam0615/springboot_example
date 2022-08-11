package com.example.security.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.security.security.CustomUserDetails;
import com.example.security.user.User;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired AdminService userService;
    
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
    public String signInSuccess(
        @AuthenticationPrincipal CustomUserDetails user,
        RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute("userAccount", user.getUsername());
        return "redirect:/user/";
    }

    @PostMapping("/signInFailure")
    public ModelAndView signInFailure(

    ) {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("user/index");

        return mv;
    }

}
