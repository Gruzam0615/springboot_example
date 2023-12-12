package com.demo.testmysql2.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "salaries")
public class Salaries {

    @Id
    @GeneratedValue
    private Long salaries_id;
    private int salaries_emp_no;
    private int salary;
    private Date from_date;
    private Date to_date;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY) // cascade = CascadeType.ALL
    @JoinColumn(name = "salaries_emp_no", insertable = false, updatable = false)
    private Employees employees;

    public Salaries(int salaries_emp_no, int salaray, Date from_date, Date to_date) {
        this.salaries_emp_no = salaries_emp_no;
        this.salary = salaray;
        this.from_date = from_date;
        this.to_date = to_date;
    }
    
}
