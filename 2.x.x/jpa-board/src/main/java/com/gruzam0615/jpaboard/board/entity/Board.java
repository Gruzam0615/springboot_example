package com.gruzam0615.jpaboard.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "board")
@Data
public class Board {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardIdx;

    private String boardTitle;
    private String boardAuthor;
    @Lob
    private String boardContent;
    private LocalDateTime boardCreatedDate;
    private LocalDateTime boardModifiedDate;

}
