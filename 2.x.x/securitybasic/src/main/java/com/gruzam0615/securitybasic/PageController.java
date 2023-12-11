package com.gruzam0615.securitybasic;

import java.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gruzam0615.securitybasic.config.security.CustomUserDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PageController {
    
    @GetMapping
    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        // log.debug(":: {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return "/index";
        }
        else {
            CustomUserDetails ud = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("user: {}", ud.toString());
            model.addAttribute("user", ud);
            return "/index";
        }
    }

    @GetMapping
    @RequestMapping(value = "/getPrincipal")
    @ResponseBody
    public String getPrincipal(Authentication auth, Principal principal) {
        if(principal != null) {
            System.out.println("principal: ");
            System.out.println(principal.getName()); // username
             System.out.println("auth: ");
            System.out.println(auth.getPrincipal().toString()); // org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, credentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[]]
            Object cre = auth.getCredentials();
            if(cre == null) { System.out.println("auth.getCredentials() is Null"); }
            else { System.out.println(auth.getCredentials().toString()); }
            System.out.println(auth.getAuthorities().toString()); // [ROLE_CLIENT]
            System.out.println(auth.getDetails().toString()); // WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=E844FBB04BC18930F0A571072602E6B4]
        }
        else {
            System.out.println("Anonymous");
        }       
       
        return "getPrincipal()";
    }

}
