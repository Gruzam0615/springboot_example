package com.demo.testmysql2.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name="employees")
@Table(name="employees")
public class Employees {
    
    @Id
    @Column(name = "emp_no")
    private int emp_no;
    private Date birth_date;
    private String first_name;
    private String last_name;
    private String gender;
    @Temporal(TemporalType.DATE)
    private Date hire_date;

    // @OneToMany(fetch = FetchType.LAZY) // cascade = {CascadeType.ALL}
    // @JoinColumn(name="titles_emp_no")
    @OneToMany(mappedBy = "employees")
    private List<Titles> titles;

    // @OneToMany(fetch = FetchType.LAZY)
    // @JoinColumn(name = "salaries_emp_no")
    @OneToMany(mappedBy = "employees")
    private List<Salaries> salaries = new ArrayList<>();

    
    
    // @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JoinColumn(name="emp_no")
    // @OrderBy(value="salary DESC")
    // private List<Salaries> salaries;

}
