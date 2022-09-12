package com.springbootex.jpa01.club.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springbootex.jpa01.club.entity.ClubCategoryEntity;
import com.springbootex.jpa01.club.entity.ClubEntity;
import com.springbootex.jpa01.club.service.ClubCategoryService;
import com.springbootex.jpa01.club.service.ClubService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/club")
public class ClubController {

    @Autowired private ClubService clubService;
    @Autowired private ClubCategoryService clubCategoryService;

    @PostMapping("/insertNewClub")
    @ResponseBody
    public void insertNewClub(ClubEntity clubEntity) {
        log.info("[insertNewClub] clubEntity: {}", clubEntity.toString());
        log.info("[insertNewClub] {} 의 카테고리 개수: {}", clubEntity.getClubName(), clubEntity.getClubCategory().size());
        ClubEntity newClub = clubService.save(clubEntity);
        if(newClub != null) {
            newClub.getClubIdx();
            for(int i = 0; i < clubEntity.getClubCategory().size(); i++) {
                ClubCategoryEntity categoryEntity = new ClubCategoryEntity();
                categoryEntity.setClubIdx(clubEntity.getClubIdx());
                categoryEntity.setClubCategory(clubEntity.getClubCategory().get(i));
                clubCategoryService.save(categoryEntity);
            }
        }
    }

    @GetMapping("/selectClubByClubIdx")
    public String selectClubByClubIdx(@RequestParam("clubIdx") Long clubIdx) {
        return null;
    }

    @GetMapping("/selectAllClubList")
    public String selectAllClubList() {
        return "club/clubList";
    }

    @GetMapping("/selectAllClubList2")
    @ResponseBody
    public ResponseEntity selectAllClubList2() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
