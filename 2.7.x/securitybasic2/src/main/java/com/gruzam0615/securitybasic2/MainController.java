package com.gruzam0615.securitybasic2;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gruzam0615.securitybasic2.util.CustomResponseEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MainController {

    @GetMapping(value="/test01")
    public ResponseEntity<Object> test01() {
        log.info("/test01 respone...");
        CustomResponseEntity result = new CustomResponseEntity("/testing01", null, LocalDateTime.now());
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    @GetMapping(value="/test02")
    public ResponseEntity<Object> test02() {
        log.info("/test02 respone...");
        CustomResponseEntity result = new CustomResponseEntity("/testing01", null, LocalDateTime.now());
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

    @GetMapping(value = "/getPrincipal")
    public ResponseEntity<Object> getPrincipal(Authentication auth, Principal principal) {
        CustomResponseEntity result = new CustomResponseEntity();
        if(principal != null) {
            Map<String, Object> resultMap = new HashMap<>();
            System.out.println("principal: ");
            System.out.println(principal.getName()); // username
             System.out.println("auth: ");
            System.out.println(auth.getPrincipal().toString()); // org.springframework.security.core.userdetails.User [Username=user, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, credentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[]]
            Object cre = auth.getCredentials();
            if(cre == null) { System.out.println("auth.getCredentials() is Null"); }
            else { System.out.println(auth.getCredentials().toString()); }
            System.out.println(auth.getAuthorities().toString()); // [ROLE_CLIENT]
            System.out.println(auth.getDetails().toString()); // WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=E844FBB04BC18930F0A571072602E6B4]
            
            resultMap.put("principal", principal.getName());
            resultMap.put("auth", auth.getPrincipal().toString());
            result.setData(resultMap);
            result.setTimeStamp(LocalDateTime.now());
            return new ResponseEntity<>(result, null, HttpStatus.OK);
        }
        else {
            System.out.println("Anonymous");
            return new ResponseEntity<>(null, null, HttpStatus.OK);
        }       
       
       
    }
    
}
