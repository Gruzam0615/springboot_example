package com.demo.testmysql2.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.demo.testmysql2.entity.Salaries;
import com.demo.testmysql2.entity.Titles;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

@Data
public class EmployeesDto {
    
    private int emp_no;
    private Date birth_date;
    private String first_name;
    private String last_name;
    private String gender;
    private Date hire_date;
    private List<Titles> titles = new ArrayList<>();
    private List<Salaries> salaries = new ArrayList<>();

    public EmployeesDto() {}

    public EmployeesDto(
        int emp_no, Date birth_date, String first_name, String last_name, String gender, Date hire_date
    ) {
        this.emp_no = emp_no;
        this.birth_date = birth_date;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.hire_date = hire_date;
    }

    public EmployeesDto(
        int emp_no, Date birth_date, String first_name, String last_name, String gender, Date hire_date, List<Titles> titles, List<Salaries> salaries
    ) {
        this.emp_no = emp_no;
        this.birth_date = birth_date;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.hire_date = hire_date;
        this.titles = titles;
        this.salaries = salaries;
    }
}