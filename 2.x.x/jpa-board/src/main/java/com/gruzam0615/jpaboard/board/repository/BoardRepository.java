package com.gruzam0615.jpaboard.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gruzam0615.jpaboard.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM board b WHERE b.boardDelete = 0 OR b.boardDelete = 1 LIMIT ?1, ?2"
    )
    public List<Board> customFindAll(int offset, int limit);
    
}
