package com.example.security.admin.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.security.user.user.entity.UserEntity;

@Repository
public interface AdminRepository extends JpaRepository<UserEntity, Long> {

    @Query(
        value="SELECT * FROM User " +
        "WHERE userAccount = ?1",
        nativeQuery=true
    )
    UserEntity findByUserAccount(String userAccount);
    
}
