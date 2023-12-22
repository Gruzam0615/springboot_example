package com.gruzam0615.jpaboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gruzam0615.jpaboard.board.entity.Board;
import com.gruzam0615.jpaboard.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private BoardService boardService;
    
    @GetMapping({"", "/", "/index"})
    public String getIndexPage(@RequestParam(name="page", defaultValue="1") int page, Model model) { 
        log.info("called index.html");
        int contentsCount = 10; // 한 페이지에 보여줄 요소의 개수
        int pagesCount = 10; // 한 번에 보여줄 페이지 개수
        if(page < 1) { page = 1; }

        // Example<Board> boardExample = Example.of(new Board(0));
        // PageRequest pageRequest = PageRequest.of(page - 1, contentsCount);
        // Page<Board> b = boardService.findAll(pageRequest);        
        // List<Board> list = b.getContent();
        // int currentPage = b.getNumber(); // 현재 페이지
        // int totalPage = b.getTotalPages(); // 총 페이지 수
        // int n = b.getNumberOfElements(); // 현재 페이지의 요소의 수
        // int n2 = b.getSize(); // 페이지 크기?
        // long n3 = b.getTotalElements(); // 요소의 총 개수
        // log.debug("{}, {}, {}, {}, {}", currentPage, totalPage, n, n2, n3);

        int currentPage = page - 1;
        Example<Board> boardExample = Example.of(new Board(0));
        long boardTotalCount = boardService.count(boardExample);
        int offset = currentPage * 10;
        List<Board> list = boardService.customFindAll(offset, contentsCount);
        // log.debug("list size: {}", list.size());
        // log.debug("board Total Count: {}", boardTotalCount);

        int totalPage = Long.valueOf(boardTotalCount).intValue() / contentsCount + 1;        
        
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
    

}
