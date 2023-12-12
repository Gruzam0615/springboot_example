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
@Entity(name = "titles")
public class Titles {
    
    @Id
    @GeneratedValue
    private Long titles_id;
    private int titles_emp_no;
    private String title;
    private Date from_date;
    private Date to_date;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY) // cascade = CascadeType.ALL
    @JoinColumn(name = "titles_emp_no", insertable = false, updatable = false)
    private Employees employees;

    public Titles(int titles_emp_no, String title, Date from_date, Date to_date) {
        this.titles_emp_no = titles_emp_no;
        this.title = title;
        this.from_date = from_date;
        this.to_date = to_date;
    }

}
