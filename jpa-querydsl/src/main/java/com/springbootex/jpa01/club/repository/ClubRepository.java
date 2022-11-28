package com.springbootex.jpa01.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootex.jpa01.club.entity.ClubEntity;

@Repository
public interface ClubRepository extends JpaRepository<ClubEntity, Long> {
    
}
