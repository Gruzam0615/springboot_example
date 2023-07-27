package com.demo.testmysql2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.demo.testmysql2.entity.Employees;
import com.demo.testmysql2.entity.QEmployees;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@SpringBootTest
@Transactional
public class EmployeesTest {
    
    @Autowired
    EntityManager em;

    JPAQueryFactory qf;

    @Test
    public void testJPQL() {
        Employees findEmployees = em.createQuery("select e from employees e where e.emp_no = :emp_no", Employees.class)
            .setParameter("emp_no", 10001)
            .getSingleResult();

        Assertions.assertThat(findEmployees.getEmp_no()).isEqualTo(10001);
    }

    @Test
    public void testQueryDsl() {
        QEmployees e = new QEmployees("e");
        qf = new JPAQueryFactory(em);

        Employees findEmployees = qf
            .select(e)
            .from(e)
            .where(e.emp_no.eq(10001))
            .fetchOne();

        Assertions.assertThat(findEmployees.getEmp_no()).isEqualTo(10001);
    }

    @Test
    public void searchQueryDsl() {
        qf = new JPAQueryFactory(em);
        QEmployees e = new QEmployees("e");

        Employees findEmployees = qf
            .selectFrom(e)
            .where(e.emp_no.eq(10001))
            .fetchOne();

        Assertions.assertThat(findEmployees.getEmp_no()).isEqualTo(10001);
    }

}
