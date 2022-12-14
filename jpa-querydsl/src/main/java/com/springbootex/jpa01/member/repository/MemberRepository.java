package com.springbootex.jpa01.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootex.jpa01.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    
}
