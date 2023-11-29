package com.gruzam0615.securitybasic.users.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private UsersRole usersRole;

    private LocalDateTime usersJoinDate;

    private String provider;

    @Column(columnDefinition = "boolean default true")
    private boolean expired;

    @Column(columnDefinition = "boolean default true")
    private boolean locked;

    @Column(columnDefinition = "boolean default true")
    private boolean enabled;

    private int signInFailureCount;

}
