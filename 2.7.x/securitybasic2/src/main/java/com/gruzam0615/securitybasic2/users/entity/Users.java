package com.gruzam0615.securitybasic2.users.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long usersIdx;
    private String usersAccount;
    private String usersPassword;
    private UsersRole usersRole;
    private LocalDateTime usersJoinDate;
    private String provider;
    private boolean expired;
    private boolean locked;
    private boolean enabled;
    private int signInFailureCount;

}

