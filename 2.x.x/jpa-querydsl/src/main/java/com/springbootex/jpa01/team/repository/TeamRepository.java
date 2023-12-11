package com.springbootex.jpa01.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootex.jpa01.team.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    
}
