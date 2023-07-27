package com.demo.testmysql2.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class EmployeesDto {
    
    private int emp_no;
    private Date birth_date;
    private String first_name;
    private String last_name;
    private String gender;
    private Date hire_date;

    private int salary;
    private Date from_date;
    private Date to_date;

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
        int emp_no, Date birth_date, String first_name, String last_name, String gender, Date hire_date,
        int salary, Date from_date, Date to_date
    ) {
        this.emp_no = emp_no;
        this.birth_date = birth_date;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.hire_date = hire_date;
        this.salary = salary;
        this.from_date = from_date;
        this.to_date = to_date;
    }


}
