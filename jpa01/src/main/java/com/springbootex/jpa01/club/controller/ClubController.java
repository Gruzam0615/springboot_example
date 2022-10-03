package com.springbootex.jpa01.club.controller;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/insertNewClub")
    @ResponseBody
    public String insertNewClub() {        
        ClubEntity club = new ClubEntity();
        club.setClubName("Test Club01");
        club.setClubCreateDate(LocalDateTime.now());

        clubService.save(club);

        return "insertNewClub";
    }
    
}
