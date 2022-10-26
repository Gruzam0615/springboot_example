package com.springbootex.jpa01.club.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(
    name="Club"
    // // column UNIQUE 설정 DDL 자동 생성시에 적용, JPA 실행에는 영향을 주지 않는다.
    // uniqueConstraints = {
    //     @UniqueConstraint(
    //         name = "UNIQUE_COLUMN",
    //         columnNames = { "clubName" }
    //     )
    // }
)
public class ClubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clubIdx;

    @Column private String clubName;
    @Column private LocalDateTime clubCreateDate;
    
    
}
