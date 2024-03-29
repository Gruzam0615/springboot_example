package com.gruzam0615.jpaboard.boardcomment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gruzam0615.jpaboard.boardcomment.entity.BoardComment;

@Repository
public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM boardcomment bc WHERE bc.boardIdx =?1 AND bc.boardCommentDelete = 0 ORDER BY bc.boardCommentCreatedDate DESC LIMIT ?2, ?3"
    )
    public List<BoardComment> customFindAll(Long boardIdx, int offset, int limit);
    
}
