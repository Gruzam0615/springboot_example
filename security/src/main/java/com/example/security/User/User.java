package com.example.security.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import lombok.Data;

@Data
@Entity(name="User")
@Table(name="User")
public class User {

    public enum userRole {
        CLIENT, ADMIN
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @Column private String userAccount;

    @Column private String userPass;

    @Column private String userName;

    @Column private String userMobile;

    @Column private String userEmail;

    @Column private String userRole;

    @Column private LocalDateTime userJoinDate;

    @Column private String provider;

    @Column private String providerId;

    @Column private String profileImage;
    

}
