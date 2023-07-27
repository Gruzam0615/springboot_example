package com.demo.testmysql2.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name="salaries")
@Table(name="salaries")
public class Salaries {

    @Id
    private int emp_no;
    private int salary;
    private Date from_date;
    private Date to_date;
    
}
