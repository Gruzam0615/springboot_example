package com.gruzam0615.jpaboard.boardcomment.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gruzam0615.jpaboard.boardcomment.entity.BoardCommentDTO;
import com.gruzam0615.jpaboard.boardcomment.entity.BoardComment;
import com.gruzam0615.jpaboard.boardcomment.entity.QBoardComment;
import com.gruzam0615.jpaboard.boardcomment.service.BoardCommentService;
import com.gruzam0615.jpaboard.util.CustomResponseEntity;
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
    @ResponseBody
    public ResponseEntity<Object> createBoardComment(@RequestBody BoardCommentDTO boardComment) {
        log.debug("requestDTO: {}", boardComment.toString());

        BoardComment requestParam = new BoardComment(boardComment.getBoardCommentAuthor(), boardComment.getBoardCommentContent(), Long.valueOf(boardComment.getBoardIdx()));
        log.debug("requestParameter: \n" + requestParam.toString());

        BoardComment bc = boardCommentService.save(requestParam);
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
    @ResponseBody
    public ResponseEntity<Object> getBoardComments(
        @RequestParam(name="boardIdx") int boardIdx, 
        @RequestParam(name="boardCommentPage", defaultValue = "1") int boardCommentPage
    ) {
        int page = boardCommentPage;
        int contentsCount = 5;
        int pagesCount = 5;
        if(page < 1) { page = 1; }

        int currentPage = page - 1;
        Example<BoardComment> boardCommentExample = Example.of(new BoardComment(0));
        long boardCommentTotalCount = boardCommentService.count(boardCommentExample);
        int offset = currentPage * pagesCount;
        List<BoardComment> list = boardCommentService.customFindAll(Long.valueOf(boardIdx), offset, contentsCount);

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
        // Example<BoardComment> example = Example.of(new BoardComment(Long.valueOf(boardIdx)));
        
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
         
        // log.debug("list: {}", list.toString());
        // log.debug("list2: {}", list2.toString());
        
        if(list.size() > 0) {
            BoardCommentDTO data = new BoardCommentDTO(currentPage + 1, totalPage, pagination, list);
            result.setMessage("success");
            result.setData(data);
            result.setTimeStamp(LocalDateTime.now());
        }
        else {
            result.setMessage("failure");
            result.setData(null);
            result.setTimeStamp(LocalDateTime.now());
        }
        // log.debug("result: {}", result.toString());

        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
    

}
