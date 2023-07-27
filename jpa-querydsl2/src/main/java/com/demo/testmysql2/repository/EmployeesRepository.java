package com.demo.testmysql2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.testmysql2.entity.Employees;

public interface EmployeesRepository extends JpaRepository<Employees, Integer> {

    @Query(
        value = "SELECT e.*, " +
        // "GROUP_CONCAT(DISTINCT t.title separator \",\") as title " +
        "GROUP_CONCAT(DISTINCT s.salary separator \",\") as salary " +
        "FROM employees e " + 
        // "INNER JOIN titles t ON t.emp_no = e.emp_no " + 
        "INNER JOIN salaries s ON s.emp_no = e.emp_no " +
        "GROUP BY e.emp_no " +
        "ORDER BY e.emp_no ASC " +
        "LIMIT 20",        
        nativeQuery = true
    )
    List<Object> EmployeesJoinFindAll();

}
