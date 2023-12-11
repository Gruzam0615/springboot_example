package com.demo.testmysql2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.demo.testmysql2.dto.EmployeesDto;
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

    // @Query(
    //     value = "SELECT e.emp_no, e.birth_date, e.first_name, e.last_name, e.gender, e.hire_date " +
    //     // "GROUP_CONCAT(s.salary SEPARATOR ',') as salary " +
    //     "FROM employees e " + 
    //     "LIMIT 10"
    //     // "LEFT JOIN salaries s ON s.emp_no = e.emp_no " +
    //     // "GROUP BY (e.emp_no) " +
    //     , nativeQuery = true
    // ) 
    // List<EmployeesDto> EmployeesSalaryJoin();

}
