package com.example.securitybasic.user.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.securitybasic.user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserAccount(String userAccount);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserEntity u SET u.signInToken = :signInToken WHERE u.userIdx = :userIdx", nativeQuery = true)
    int updateSignInToken(@Param(value = "userIdx") Long userIdx, @Param(value = "signInToken") String signInToken);

    @Query(value = "SELECT * FROM UserEntity u WHERE u.signInToken = ?1", nativeQuery = true)
    UserEntity findBySignInToken(String signInToken);
    
}
