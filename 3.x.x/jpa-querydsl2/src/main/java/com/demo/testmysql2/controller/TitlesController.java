package com.demo.testmysql2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.testmysql2.service.TitlesService;

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
