package com.springex.jpa.groupmember;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.springex.jpa.user.UserEntity;

import lombok.Data;

@Data
@Entity
@Table(name="GroupMemberEntity")
public class GroupMemberEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long GroupMemberIdx;

    @Column private Long groupIdx;
    @Column private Long userIdx;
    @Column private LocalDateTime joinDate;

    // @OneToOne
    // @JoinTable(
    //     name="UserEntity",
    //     joinColumns = @JoinColumn(name="userIdx"),
    //     inverseJoinColumns = @JoinColumn(name="userIdx")
    // )
    // private UserEntity userEntity;

}
