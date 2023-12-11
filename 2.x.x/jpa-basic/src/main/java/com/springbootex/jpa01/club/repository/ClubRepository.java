package com.springbootex.jpa01.club.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootex.jpa01.club.entity.ClubEntity;

@Repository
public interface ClubRepository extends JpaRepository<ClubEntity, Long> {
    
}

// @Repository
// public class ClubRepository {

//     @PersistenceContext
//     private EntityManager em;

//     public Long save(ClubEntity clubEntity) {
//         em.persist(clubEntity);
//         return clubEntity.getClubIdx();
//     }

// }