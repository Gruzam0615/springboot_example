package com.gruzam0615.jpaboard.board.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gruzam0615.jpaboard.board.entity.Board;
import com.gruzam0615.jpaboard.board.service.BoardService;
import com.gruzam0615.jpaboard.util.CustomResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping(value = "/board")
@Slf4j
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/create")
    public String getCreatePage() {
        log.info("called /board/create");
        return "board/create";
    }
    
    @PostMapping("/createAction")
    @ResponseBody
    public ResponseEntity<Object> createAction(@RequestBody Board board) {
        log.debug("parameter: \n" + board.toString());
        Board b = boardService.save(board);
        CustomResponseEntity result = new CustomResponseEntity();
        if(b != null) {
            log.info("{}", b.getBoardIdx());
            result.setMessage("board 글 작성 완료");
            result.setData("/");
            result.setTimeStamp(LocalDateTime.now());
            return new ResponseEntity<>(result, null, HttpStatus.OK);
        } else {
            log.info("board 글 작성 실패 글 제목: {}", board.getBoardTitle());
            result.setMessage("content create Failure");
            result.setData("/board/create");
            result.setTimeStamp(LocalDateTime.now());
            return new ResponseEntity<>(result, null, HttpStatus.OK);
        }
    }
    
    @GetMapping("/list")
    public String getBoardList(@RequestParam(name="page", defaultValue="1") int page, Model model) {
       int contentsCount = 10; // 한 페이지에 보여줄 요소의 개수
        int pagesCount = 10; // 한 번에 보여줄 페이지 개수
        if(page < 1) { page = 1; }
        PageRequest pageRequest = PageRequest.of(page - 1, contentsCount);
        Page<Board> b = boardService.findAll(pageRequest);
        List<Board> list = b.getContent();
        int currentPage = b.getNumber(); // 현재 페이지
        int totalPage = b.getTotalPages(); // 총 페이지 수
        // int n = b.getNumberOfElements(); // 현재 페이지의 요소의 수
        // int n2 = b.getSize(); // 페이지 크기?
        // long n3 = b.getTotalElements(); // 요소의 총 개수
        // log.debug("{}, {}, {}, {}, {}", currentPage, totalPage, n, n2, n3);
        List<Integer> pagination = new ArrayList<>();
        if(totalPage > pagesCount) {
            int tempNumber = page / pagesCount;
            if(tempNumber == 0) {
                for(int i = 0; i < 10; i++) {
                    pagination.add(i + 1);
                }
            }
            else {
                tempNumber = tempNumber * 10;
                for(int i = tempNumber; i < tempNumber + pagesCount; i++) {
                    pagination.add(i + 1);
                }
            }
        }
        else {
            for(int i = 0; i < totalPage; i++) {
                pagination.add(i+1);
            }
        }
        
       
        model.addAttribute("page", currentPage + 1);
        model.addAttribute("lastPage", totalPage);
        model.addAttribute("list", list);
        model.addAttribute("pagination", pagination);
        
        return "index";
    }
    
    @GetMapping("/view")
    public String getMethodName(@RequestParam(name="boardIdx") long boardIdx, Model model) {
        Board b = boardService.getReferenceById(boardIdx);

        model.addAttribute("board", b);
        return "board/view";
    }

    @PostMapping("/updateAction")
    @ResponseBody
    public ResponseEntity<Object> updateAction(@RequestBody Board board) {
        log.debug("parameter: \n" + board.toString());
        Board b = boardService.updateBoard(board);
        CustomResponseEntity result = new CustomResponseEntity();
        if(b != null) {
            log.info("{}", b.getBoardIdx());
            result.setMessage("board 글 수정 완료");
            result.setData("/");
            result.setTimeStamp(LocalDateTime.now());
            return new ResponseEntity<>(result, null, HttpStatus.OK);
        } else {
            log.info("board 글 수정 실패: {}", board.getBoardTitle());
            result.setMessage("content create Failure");
            result.setData("/board/view?boardIdx=" + board.getBoardIdx());
            result.setTimeStamp(LocalDateTime.now());
            return new ResponseEntity<>(result, null, HttpStatus.OK);
        }
    }
    
    @PatchMapping("/deleteAction")
    @ResponseBody
    public ResponseEntity<Object> deleteAction(@RequestParam(value="boardIdxList") List<Long> boardIdxList) {
        CustomResponseEntity result = new CustomResponseEntity();
        List<Long> resultData = new ArrayList<>();

        for(int i = 0; i < boardIdxList.size(); i++) {
            Board b = boardService.deleteBoard(boardIdxList.get(i));
            resultData.add(b.getBoardIdx());
        }

        result.setMessage("해당 board 글 삭제");
        result.setData(resultData);
        result.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }    
    
}
