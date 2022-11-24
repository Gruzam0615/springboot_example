package com.example.security.user.item.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/item")
public class ItemController {
    
    @GetMapping("/test01")
    @ResponseBody
    public String test01() {
        return "/item/test01";
    }

    @PostMapping("/test02")
    @ResponseBody
    public String test02(
        @RequestHeader String accessToken
    ) {
        log.info("[test02] user's accessToken: {}", accessToken);
        return "/item/test02";
    }

}
