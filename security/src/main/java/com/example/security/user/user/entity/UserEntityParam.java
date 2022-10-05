package com.example.security.user.user.entity;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UserEntityParam {
    private String userAccount;
    private String userPass;
    private String userRole;
    private LocalDateTime userJoinDate;
    private MultipartFile profileImage;
}
