package com.gruzam0615.securitybasic.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gruzam0615.securitybasic.users.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
    
}
