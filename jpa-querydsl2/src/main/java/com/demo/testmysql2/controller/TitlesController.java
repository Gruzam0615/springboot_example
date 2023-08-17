package com.demo.testmysql2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.testmysql2.entity.Employees;
import com.demo.testmysql2.entity.Titles;
import com.demo.testmysql2.service.TitlesService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/titles")
public class TitlesController {

    @Autowired
    private TitlesService titlesService;

    @GetMapping("/findAllTitles")
    public List<String> findAllTitles() {
        return titlesService.findAllTitle();
    } 

    // public ResponseEntity<Object> EmployeesFindAll(HttpServletRequest request, HttpServletResponse response) {
    //     List<Employees> data = employeesService.findAll();
    //     data = data.subList(0, 10);
    //     return new ResponseEntity<Object>(data, null, HttpServletResponse.SC_OK);
    // }
    
}
