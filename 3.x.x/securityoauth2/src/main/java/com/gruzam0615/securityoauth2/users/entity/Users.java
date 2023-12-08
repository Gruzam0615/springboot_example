package com.gruzam0615.securityoauth2.users.entity;

import java.time.LocalDateTime;

import com.gruzam0615.securityoauth2.security.oauth2.attribute.ProviderType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
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

    private String usersEmail;

    @Enumerated(EnumType.STRING)
    private UsersRole usersRole;
    
    private LocalDateTime usersJoinDate;

    private String profilePicture;

    @Enumerated(EnumType.STRING)
    private ProviderType provider;

    private String providerId;

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

    @Builder
    public Users(String usersAccount, String usersEmail, String profilePicture, UsersRole usersRole) {
        this.usersAccount = usersAccount;
        this.usersEmail = usersEmail;
        this.profilePicture = profilePicture;
        this.usersRole = usersRole;
    }

    @Builder
    public Users(String usersEmail, String profilePicture, UsersRole usersRole) {
        this.usersEmail = usersEmail;
        this.profilePicture = profilePicture;
        this.usersRole = usersRole;
    }

    // public Users update(String profilePicture) {
    //     this.profilePicture = profilePicture;
    //     return this;
    // }

}

