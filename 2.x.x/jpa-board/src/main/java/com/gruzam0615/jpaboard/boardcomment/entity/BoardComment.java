package com.gruzam0615.jpaboard.boardcomment.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
@Table(name = "boardcomment")
@Entity
public class BoardComment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardCommentIdx;    

    private String boardCommentAuthor;

    @Lob
    private String boardCommentContent;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int boardCommentDelete;

    private LocalDateTime boardCommentCreatedDate;

    private LocalDateTime boardCommentModifiedDate;

    private Long boardIdx;

    public BoardComment(Long boardIdx) {
        this.boardIdx = boardIdx;
    }

    public BoardComment(int boardCommentDelete) {
        this.boardCommentDelete = boardCommentDelete;
    }

}
