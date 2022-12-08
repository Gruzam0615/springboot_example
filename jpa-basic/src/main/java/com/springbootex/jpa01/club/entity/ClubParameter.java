package com.springbootex.jpa01.club.entity;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ClubParameter {
    
    private Long clubIdx;
    private String clubName;
    private LocalDateTime clubCreateDate;
    private String[] clubCategory;
    private MultipartFile clubIcon;

}
