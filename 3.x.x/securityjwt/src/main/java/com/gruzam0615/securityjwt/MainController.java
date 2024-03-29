package com.gruzam0615.securityjwt;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gruzam0615.securityjwt.util.CustomResponseEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MainController {
    
    @GetMapping(value="/test01")
    public ResponseEntity<Object> test01() {
        log.info("/test01 requested...");
        // CustomResponseEntity result = new CustomResponseEntity("testing01 message", LocalDateTime.now());
        CustomResponseEntity result = new CustomResponseEntity("/testing01", null, LocalDateTime.now());
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }

}
