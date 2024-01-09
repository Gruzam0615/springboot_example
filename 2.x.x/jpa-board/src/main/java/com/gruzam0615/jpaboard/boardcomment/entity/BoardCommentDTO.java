package com.gruzam0615.jpaboard.boardcomment.entity;

import java.util.List;

import javax.persistence.Lob;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BoardCommentDTO {

    private String boardCommentAuthor;
    @Lob
    private String boardCommentContent;
    private int boardIdx;

    private int boardCommentPage;
    private int boardCommentLastPage;
    private List<Integer> boardCommentPagination;
    private List<BoardComment> list;

    public BoardCommentDTO(String boardCommentAuthor, String boardCommentContent, int boardIdx) {
        this.boardCommentAuthor = boardCommentAuthor;
        this.boardCommentContent = boardCommentContent;
        this.boardIdx = boardIdx;
    }

    public BoardCommentDTO(int boardCommentPage, int boardCommentLastPage, List<Integer> boardCommentPagination, List<BoardComment> list) {
        this.boardCommentPage = boardCommentPage;
        this.boardCommentLastPage = boardCommentLastPage;
        this.boardCommentPagination = boardCommentPagination;
        this.list = list;
    }
    
}
