package com.springex.jpa.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.springex.jpa.groupmember.GroupMemberEntity;

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
    @Column private String userRole;

    @OneToOne
    @JoinColumn(name="userIdx")
    private GroupMemberEntity groupMemberEntity;
    

}
