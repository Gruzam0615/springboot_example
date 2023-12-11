package com.springbootex.jpa01.club.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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

    @Column(nullable = false)
    private String clubName;

    @Column(nullable = false)
    private LocalDateTime clubCreateDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "clubIdx")
    private List<ClubCategoryEntity> clubCategoryEntitys = new ArrayList<>();

    public void addCategory(ClubCategoryEntity clubCategoryEntity) {
        clubCategoryEntitys.add(clubCategoryEntity);
    }

}
