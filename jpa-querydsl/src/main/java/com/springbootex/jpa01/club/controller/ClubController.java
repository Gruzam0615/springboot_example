package com.springbootex.jpa01.club.controller;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springbootex.jpa01.club.entity.ClubEntity;
import com.springbootex.jpa01.club.service.ClubService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/club")
public class ClubController {

    @Autowired private ClubService clubService;

    /**
     * club 목록 페이지로 이동하는 컨트롤러
     * @return
     */
    @GetMapping("/list")
    public String clubListView() {
        return clubService.clubListView();
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
    public String clubInsert() {
        ClubEntity club = new ClubEntity();
        club.setClubName("Test Club01");
        club.setClubCreateDate(LocalDateTime.now());

        clubService.save(club);

        return "insertNewClub";
    }
    
}
