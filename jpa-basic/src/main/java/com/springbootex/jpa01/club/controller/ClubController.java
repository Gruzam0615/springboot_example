package com.springbootex.jpa01.club.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springbootex.jpa01.club.entity.ClubCategoryEntity;
import com.springbootex.jpa01.club.entity.ClubEntity;
import com.springbootex.jpa01.club.entity.ClubParameter;
import com.springbootex.jpa01.club.service.ClubService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/club")
public class ClubController {

    @Autowired 
    private ClubService clubService;

    /**
     * club 목록 페이지로 이동하는 컨트롤러
     * @return
     */
    @GetMapping("/list")
    public String clubListView() {
        return clubService.clubListView();
    }

    @GetMapping("/selectList")
    @ResponseBody
    public List<ClubEntity> selectList() {
        return clubService.findAll();
    }
    
    /**
     * club 등록 페이지로 이동하는 컨트롤러
     * @return
     */
    @GetMapping("/insert")
    public String clubInsertView() {
        return clubService.clubInsertView();
    }

    /**
     * club을 등록하는 컨트롤러
     * @return
     */
    @PostMapping("/insert")
    @ResponseBody
    public String clubInsert(ClubParameter clubParameter) {
        log.info("[/club/insert] club: {}", clubParameter.toString());
        if(!clubParameter.getClubIcon().equals(null)) {
            log.info("[/club/insert] fileName: {}", clubParameter.getClubIcon().getOriginalFilename());
            log.info("[/club/insert] fileSize: {}", clubParameter.getClubIcon().getSize());
        }

        ClubEntity club = new ClubEntity();
        club.setClubName(clubParameter.getClubName());
        // clubService.save(club);

        ClubCategoryEntity clubCategoryEntity = new ClubCategoryEntity();

        return "saved";
        
    }
    
}
