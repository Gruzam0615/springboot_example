package com.demo.testmysql2.dto;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TitlesDto {
    
    private int emp_no;
    private String title;
    private Date from_date;
    private Date to_date;

}
