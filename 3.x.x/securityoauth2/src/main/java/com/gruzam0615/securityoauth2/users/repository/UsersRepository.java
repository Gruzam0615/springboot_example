package com.gruzam0615.securityoauth2.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gruzam0615.securityoauth2.users.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query(nativeQuery = true,
    value = "SELECT * FROM Users u WHERE u.usersAccount = :usersAccount")
    public Users findUsersByUsersAccount(@Param("usersAccount") String usersAccount);

    @Query(nativeQuery = true,
    value = "SELECT * FROM Users u WHERE u.usersEmail = :usersEmail")
    public Users findUsersByUsersEmail(@Param("usersEmail") String usersEmail);

    @Query(nativeQuery = true,
    value = "SELECT * FROM Users u WHERE u.signInToken = :signInToken")
    public Users findUsersByToken(@Param("signInToken") String signInToken);
    
}
