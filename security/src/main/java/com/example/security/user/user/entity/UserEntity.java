package com.example.security.user.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity(name="UserEntity")
@Table(name="UserEntity")
public class UserEntity {

    public enum userRole {
        CLIENT, ADMIN
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @Column(nullable = false, length = 20) private String userAccount;

    @Column(nullable = false, length = 20) private String userPass;

    @Column(nullable = false, length = 30) private String userName;

    @Column(nullable = false, length = 13) private String userMobile;

    @Column(nullable = false, length = 40) private String userEmail;

    @Column(nullable = false, length = 20) private String userRole;

    @Column private LocalDateTime userJoinDate;

    @Column(columnDefinition = "varchar(300) default 'local'") private String provider;

    @Column(nullable = true, length = 300) private String providerId;

    @Column(nullable = true, length = 300) private String profileImage;
    

}
