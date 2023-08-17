package com.demo.testmysql2.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.testmysql2.dto.SalariesDto;
import com.demo.testmysql2.entity.Salaries;
import com.demo.testmysql2.service.SalariesServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/salaries")
public class SalariesController {

    @Autowired
    private SalariesServices salariesServices;

    @GetMapping("/find1")
    public List<Salaries> find1(SalariesDto salariesDto) {
        log.info("input emp_no is {}", salariesDto.getEmp_no());
        Long converted_emp_no = (long) salariesDto.getEmp_no();
        return salariesServices.findDtosById(converted_emp_no);
    }
    
}
