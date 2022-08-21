package com.example.oauth2.userrefreshtoken;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "UserRefreshToken")
public class UserRefreshToken {
    @JsonIgnore
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long refreshTokenSeq;

    @Column(length = 64, unique = true, nullable=false)
    private String userId;

    @Column(length = 256, nullable=false)
    private String refreshToken;

    public UserRefreshToken(String userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }
}
