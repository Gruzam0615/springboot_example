package com.gruzam0615.securitybasic2;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    
}
