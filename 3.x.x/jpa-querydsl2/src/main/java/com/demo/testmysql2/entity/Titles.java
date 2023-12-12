package com.demo.testmysql2.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name="titles")
@Table(name="titles")
public class Titles {
    
    @Id
    private Long titles_id;
    private int titles_emp_no;
    private String title;
    private Date from_date;
    private Date to_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_no")
    private Employees employees;

    public Titles(int titles_emp_no, String title, Date from_date, Date to_date) {
        this.titles_emp_no = titles_emp_no;
        this.title = title;
        this.from_date = from_date;
        this.to_date = to_date;
    }

}
