package com.demo.testmysql2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.testmysql2.dto.EmployeesDto;
import com.demo.testmysql2.entity.Employees;
import com.demo.testmysql2.service.EmployeesService;
import com.demo.testmysql2.utility.CustomResponseEntity;
import com.demo.testmysql2.utility.exception.CustomExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;

    @GetMapping("/EmployeesFindAll")
    public ResponseEntity<Object> EmployeesFindAll(HttpServletRequest request, HttpServletResponse response) {
        List<Employees> data = employeesService.findAll();
        data = data.subList(0, 10);
        return new ResponseEntity<Object>(data, null, HttpServletResponse.SC_OK);
    }

    @GetMapping("/EmployeesFindAll2")
    public ResponseEntity<Object> EmployeesFindAll2(HttpServletRequest request, HttpServletResponse response) {
        List<EmployeesDto> data = employeesService.employeesFindAll2();
        if(data != null) {
        //    data = data.subList(0, 10);
            return new ResponseEntity<Object>(data, null, HttpServletResponse.SC_OK);
        }
        else {
            System.out.println("SSSSS");
            CustomResponseEntity result = new CustomResponseEntity(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
            return new ResponseEntity<Object>(result, null, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/EmployeesFindAll3")
    public ResponseEntity<Object> employeesFindAll3(HttpServletRequest request, HttpServletResponse response) {
        List<Employees> data = employeesService.employeesFindAll3();
        if(data != null) {
        //    data = data.subList(0, 10);
            return new ResponseEntity<Object>(data, null, HttpServletResponse.SC_OK);
        }
        else {
            System.out.println("SSSSS");
            CustomResponseEntity result = new CustomResponseEntity(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
            return new ResponseEntity<Object>(result, null, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/EmployeesFindAll4")
    public ResponseEntity<Object> employeesFindAll4(HttpServletRequest request, HttpServletResponse response) {
        List<EmployeesDto> data = employeesService.employeesFindAll4();
        if(data != null) {
        //    data = data.subList(0, 10);
            return new ResponseEntity<Object>(data, null, HttpServletResponse.SC_OK);
        }
        else {
            System.out.println("SSSSS");
            CustomResponseEntity result = new CustomResponseEntity(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
            return new ResponseEntity<Object>(result, null, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/EmployeesFindAll5")
    public ResponseEntity<Object> employeesFindAll5(HttpServletRequest request, HttpServletResponse response) {
        List<EmployeesDto> data = employeesService.employeesFindAll4();
        if(data != null) {
        //    data = data.subList(0, 10);
            return new ResponseEntity<Object>(data, null, HttpServletResponse.SC_OK);
        }
        else {
            System.out.println("SSSSS");
            CustomResponseEntity result = new CustomResponseEntity(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
            return new ResponseEntity<Object>(result, null, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByEmpNo")
    public ResponseEntity<Object> findByEmpNo(HttpServletRequest request, HttpServletResponse response, EmployeesDto employeesDto) {
        
        List<Employees> data = employeesService.findByEmpNo(employeesDto.getEmp_no());
        if(data != null) {
        //    data = data.subList(0, 10);
            return new ResponseEntity<Object>(data, null, HttpServletResponse.SC_OK);
        }
        else {
            System.out.println("SSSSS");
            CustomResponseEntity result = new CustomResponseEntity(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
            return new ResponseEntity<Object>(result, null, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/EmployeesJoinFindAll")
    public ResponseEntity<Object> EmployeesTitlesF(HttpServletRequest request, HttpServletResponse response) throws Exception, CustomExceptionHandler {
        List<Object> data = employeesService.EmployeesJoinFindAll();
        if(data != null) {
            // data = data.subList(0, 10);
            return new ResponseEntity<Object>(data, null, HttpServletResponse.SC_OK);
        }
        else {
            System.out.println("SSSSS");
            CustomResponseEntity result = new CustomResponseEntity(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null);
            return new ResponseEntity<Object>(result, null, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
