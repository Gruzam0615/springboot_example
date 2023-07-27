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
    
}
