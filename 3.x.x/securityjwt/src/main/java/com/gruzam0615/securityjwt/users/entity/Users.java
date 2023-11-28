package com.gruzam0615.securityjwt.users.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @param long usersIdx
 */

@NoArgsConstructor
@Data
@Entity
public class Users {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long usersIdx;
    
    @Column(nullable=false)
    private String usersAccount;

    @Column(nullable=false)
    private String usersPassword;

    @Enumerated(EnumType.STRING)
    private UsersRole usersRole;
    
    private LocalDateTime usersJoinDate;

    private String provider;

    private boolean expired;

    private boolean locked;

    private boolean enabled;

    private int signInFailureCount;

    private String signInToken;

    public Users(String usersAccount, String usersPassword) {
        this.usersAccount = usersAccount;
        this.usersPassword = usersPassword;
    }

    public Users(long usersIdx, String usersAccount, String usersRole, LocalDateTime usersJoinDate) {
        this.usersIdx = usersIdx;
        this.usersAccount = usersAccount;
        this.usersRole = UsersRole.valueOf(usersRole);
        this.usersJoinDate = usersJoinDate;
    }

}

