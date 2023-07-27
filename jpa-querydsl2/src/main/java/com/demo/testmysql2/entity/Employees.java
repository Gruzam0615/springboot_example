package com.demo.testmysql2.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name="employees")
@Table(name="employees")
public class Employees {
    
    @Id
    private int emp_no;
    private Date birth_date;
    private String first_name;
    private String last_name;
    private String gender;
    private Date hire_date;

    @OneToMany(fetch = FetchType.LAZY) // cascade = {CascadeType.ALL}
    @JoinColumn(name="emp_no")
    private List<Titles> titles;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="emp_no")
    private List<Salaries> salaries;

}
