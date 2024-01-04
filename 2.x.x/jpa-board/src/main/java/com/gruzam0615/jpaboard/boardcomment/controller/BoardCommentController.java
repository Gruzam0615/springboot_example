package com.gruzam0615.jpaboard.boardcomment.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gruzam0615.jpaboard.board.entity.Board;
import com.gruzam0615.jpaboard.boardcomment.entity.BoardComment;
import com.gruzam0615.jpaboard.boardcomment.entity.QBoardComment;
import com.gruzam0615.jpaboard.boardcomment.service.BoardCommentService;
import com.gruzam0615.jpaboard.util.CustomResponseEntity;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping(value = "/boardComment")
@Slf4j
public class BoardCommentController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BoardCommentService boardCommentService;

    @PostMapping("/createBoardComment")
    public ResponseEntity<Object> createBoardComment(@RequestBody BoardComment boardComment) {
        log.debug("parameter: \n" + boardComment.toString());
        BoardComment bc = boardCommentService.save(boardComment);
        CustomResponseEntity result = new CustomResponseEntity();

        if(bc != null) {
            log.info("{}", bc.getBoardCommentIdx());
            result.setMessage("새로운 boardComment 작성 완료");
            result.setData(true);
            result.setTimeStamp(LocalDateTime.now());
            return new ResponseEntity<>(result, null, HttpStatus.OK);
        } else {
            log.info("board 글 작성 실패 글 제목: {}", bc.getBoardIdx());
            result.setMessage("새로운 boardComment 작성 실패");
            result.setData(false);
            result.setTimeStamp(LocalDateTime.now());
            return new ResponseEntity<>(result, null, HttpStatus.OK);
        }
    }
    
    
    @GetMapping("/getBoardComments")
    public ResponseEntity<Object> getBoardComments(@RequestParam(name="boardIdx") int boardIdx) {

        int page = 1;
        int contentsCount = 20;
        int pagesCount = 5;
        if(page < 1) { page = 1; }

        int currentPage = page - 1;
        Example<BoardComment> boardCommentExample = Example.of(new BoardComment(0));
        long boardCommentTotalCount = boardCommentService.count(boardCommentExample);
        int offset = currentPage * page;
        List<BoardComment> list = boardCommentService.customFindAll(offset, contentsCount);

        int totalPage = Long.valueOf(boardCommentTotalCount).intValue() / contentsCount + 1;

        List<Integer> pagination = new ArrayList<>();
        if(totalPage > pagesCount) {
            int tempNumber = page / pagesCount;
            if(tempNumber == 0) {
                for(int i = 0; i < pagesCount; i++) {
                    pagination.add(i + 1);
                }
            }
            else {
                tempNumber = tempNumber * pagesCount;
                for(int i = tempNumber; i < tempNumber + pagesCount; i++) {
                    pagination.add(i + 1);
                }
            }
        }
        else {
            for(int i = 0; i < totalPage; i++) {
                pagination.add(i + 1);
            }
        }


        log.info("Searching boardIdx: {} 's Comment", boardIdx);
        CustomResponseEntity result = new CustomResponseEntity();
        JPAQueryFactory qf = new JPAQueryFactory(entityManager);
        QBoardComment bc = new QBoardComment("bc");

        // List<BoardComment> list = null;
        Example<BoardComment> example = Example.of(new BoardComment(Long.valueOf(boardIdx)));
        
        // List<BoardComment> list = boardCommentService.findBy(example, query -> query
        //     .sortBy(Sort.by("boardCommentCreatedDate").descending())
        //     .all()
        // );

        List<BoardComment> list2 = qf
            .select(bc)
            .from(bc)
            .where(
                bc.boardIdx.eq(Long.valueOf(boardIdx))
                .and(bc.boardCommentDelete.eq(0))
            )
            .orderBy(bc.boardCommentCreatedDate.asc())
            .fetch();
         
        log.debug("list: {}", list.toString());
        // log.debug("list2: {}", list2.toString());
        
        result.setData(list);
        result.setTimeStamp(LocalDateTime.now());
        log.debug("result: {}", result.toString());

        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
    

}
