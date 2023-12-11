package com.springbootex.jpa01.member.repository;

import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springbootex.jpa01.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    
    @Query(
        value = "SELECT * FROM Member m WHERE m.memberId = :memberId",
        nativeQuery = true
    )
    public Member findByMemberId(Long memberId);

}
