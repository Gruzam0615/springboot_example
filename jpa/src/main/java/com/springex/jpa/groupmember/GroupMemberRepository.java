package com.springex.jpa.groupmember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMemberEntity, Long> {

    @Query(
        value="SELECT GroupMemberEntity.groupMemberIdx, UserEntity.userName from GroupMemberEntity " +
            "JOIN UserEntity " + 
            "WHERE GroupMemberEntity.userIdx=?1",
        nativeQuery = true
    )
    GetByUserIdx getByUserIdx(Long userIdx);

}
