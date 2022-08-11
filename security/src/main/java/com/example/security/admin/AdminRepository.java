package com.example.security.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.security.user.User;

@Repository
public interface AdminRepository extends JpaRepository<User, Long> {

    @Query(
        value="SELECT * FROM User " +
        "WHERE userAccount = ?1",
        nativeQuery=true
    )
    User findByUserAccount(String userAccount);
    
}
