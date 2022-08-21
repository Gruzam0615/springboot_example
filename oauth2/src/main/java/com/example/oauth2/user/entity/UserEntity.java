package com.example.oauth2.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="UserEntity")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userIdx;

    @Column private String userAccount;
    @Column private String userPass;
    @Column private String userName;
    @Column private String userMobile;
    @Column private String userEmail;
    @Column
    @Enumerated(EnumType.STRING)
    private RoleType userRole;
    @Column
    @Enumerated(EnumType.STRING)
    private ProviderType provider;
    // @Column private String provider;
    @Column private String providerAccount;
    @Column private String userProfileImg;
    @Column private LocalDateTime userSignUpDate;

    public UserEntity(String userAccount2, String userName2, String userEmail2, RoleType userRole2,
            String userProfileImage) {
    }

}
