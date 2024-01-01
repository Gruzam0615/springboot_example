package com.gruzam0615.jpaboard.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>boardIdx 게시글 번호 (Long)</p>
 * <p>boardTitle 게시글 제목 (String)</p>
 * <p>boardAuthor 게시글 작성자 (String></p>
 * <p>boardContent 게시글 내용 (Blob) 텍스트 에디터 라이브러리를 사용하기 때문에 Blob으로 지정</p>
 * <p>boardDelete 게시글 공개여부 (int) 0=공개, 1=비공개, 2=삭제대기</p>
 * <p>boardCreatedDate 게시글이 생성될 때의 타임스탬프 (LocalDateTime)</p>
 * <p>boardCreatedDate 게시글이 수정될 때의 타임스탬프 (LocalDateTime)</p> 
 */
@Entity
@Table(name = "board")
@Getter
@Setter
@NoArgsConstructor
public class Board {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardIdx;

    private String boardTitle;
    private String boardAuthor;
    @Lob
    private String boardContent;
    @Column(nullable = false)
    @ColumnDefault("0")
    private int boardDelete;
    private LocalDateTime boardCreatedDate;
    private LocalDateTime boardModifiedDate;

    @Builder
    public Board(Long boardIdx, String boardTitle, String boardAuthor, String boardContent, int boardDelete, LocalDateTime boardCreatedDate, LocalDateTime boardModifiedDate) {
        this.boardIdx = boardIdx;
        this.boardTitle = boardTitle;
        this.boardAuthor = boardAuthor;
        this.boardContent = boardContent;
        this.boardDelete = boardDelete;
        this.boardCreatedDate = boardCreatedDate;
        this.boardModifiedDate = boardModifiedDate;
    }

    public Board(int boardDelete) {
        this.boardDelete = boardDelete;
    }

}
