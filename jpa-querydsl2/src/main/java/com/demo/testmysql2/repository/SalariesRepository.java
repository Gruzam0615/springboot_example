package com.demo.testmysql2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.testmysql2.entity.Salaries;

@Repository
public interface SalariesRepository extends JpaRepository<Salaries, Long> {
    
}
