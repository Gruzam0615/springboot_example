package com.gruzam0615.jpaboard.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gruzam0615.jpaboard.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    
}
