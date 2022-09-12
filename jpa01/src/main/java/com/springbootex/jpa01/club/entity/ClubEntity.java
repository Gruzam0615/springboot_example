package com.springbootex.jpa01.club.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name="Club")
public class ClubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubIdx;
    @Column private String clubName;
    @Column private LocalDateTime clubRegistrationDate;
    @Transient private List<String> clubCategory;
    // @OneToMany(mappedBy="clubEntity")
    // @JsonIgnore
    // private List<ClubCategoryEntity> clubCategory = new ArrayList<>();

    // public void addClubCategory(ClubCategoryEntity clubCategoryEntity) {
    //     this.clubCategory.add(clubCategoryEntity);
    //     if(clubCategoryEntity.getClubEntity() != this) {
    //         clubCategoryEntity.setClubEntity(this);
    //     }
    // }

    @Builder
    public ClubEntity(String clubName) {
        this.clubName = clubName;
    }
    
}
