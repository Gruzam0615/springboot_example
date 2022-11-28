package com.springbootex.jpa01.club.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="ClubCategory")
public class ClubCategoryEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubCategoryIdx;
    private Long clubIdx;
    private String clubCategory;

    // @OneToMany(targetEntity = ClubEntity.class)
    // @JoinColumn(name="clubIdx", nullable=false)
    // private ClubEntity clubEntity;

    // public void setClubEntity(ClubEntity clubEntity) {
    //     this.clubEntity = clubEntity;
    //     if(!clubEntity.getClubCategory().contains(this)) {
    //         clubEntity.getClubCategory().add(this);
    //     }
    // }

}
