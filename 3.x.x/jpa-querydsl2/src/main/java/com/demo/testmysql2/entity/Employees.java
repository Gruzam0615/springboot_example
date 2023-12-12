package com.demo.testmysql2.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "employees")
public class Employees {
    
    @Id
    @GeneratedValue
    @Column(name = "emp_no")
    private int emp_no;
    private Date birth_date;
    private String first_name;
    private String last_name;
    private String gender;
    @Temporal(TemporalType.DATE)
    private Date hire_date;

    @JsonManagedReference
    @OneToMany(mappedBy = "employees")
    private List<Titles> titles = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "employees")
    private List<Salaries> salaries = new ArrayList<>();


}
