package com.demo.testmysql2.dto;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SalariesDto {

    private int emp_no;
    private int salary;
    private Date from_date;
    private Date to_date;

    public SalariesDto(int emp_no, int salary, Date from_date, Date to_date) {
        this.emp_no = emp_no;
        this.salary = salary;
        this.from_date = from_date;
        this.to_date = to_date;
    }
    
}
