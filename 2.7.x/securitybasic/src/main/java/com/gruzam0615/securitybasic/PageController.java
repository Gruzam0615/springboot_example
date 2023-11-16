package com.gruzam0615.securitybasic;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
    
    @GetMapping
    @RequestMapping(value = "/")
    public String index() {
        return "/index";
    }

    @GetMapping
    @RequestMapping(value = "/getPrincipal")
    @ResponseBody
    public String getPrincipal(Authentication auth, Principal principal) {
        System.out.println("principal: ");
        System.out.println(principal.getName()); // username
        System.out.println("auth: ");
        System.out.println(auth.getPrincipal().toString()); // org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, credentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[]]
        Object cre = auth.getCredentials();
        if(cre == null) { System.out.println("auth.getCredentials() is Null"); }
        else { System.out.println(auth.getCredentials().toString()); }
        System.out.println(auth.getAuthorities().toString()); // [ROLE_CLIENT]
        System.out.println(auth.getDetails().toString()); // WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=E844FBB04BC18930F0A571072602E6B4]
        return "getPrincipal()";
    }

}
