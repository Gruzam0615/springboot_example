package com.springbootex.jpa01.club.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Club")
public class ClubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clubIdx;

    @Column private String clubName;
    @Column private LocalDateTime clubCreateDate;
    
    
}
