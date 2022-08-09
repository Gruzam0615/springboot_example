package com.example.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
        value="SELECT * FROM User " +
        "WHERE userAccount = ?1",
        nativeQuery=true
    )
    User findByUserAccount(String userAccount);
    
}
