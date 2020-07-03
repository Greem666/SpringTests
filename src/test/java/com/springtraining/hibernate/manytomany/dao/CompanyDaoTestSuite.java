package com.springtraining.hibernate.manytomany.dao;

import com.springtraining.hibernate.manytomany.Company;
import com.springtraining.hibernate.manytomany.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyDaoTestSuite {
    @Autowired
    private CompanyDao companyDao;

    @Test
    public void testSaveManyToMany() {
        // Given
        Employee employee1 = new Employee("Employee1", "Employee1");
        Employee employee2 = new Employee("Employee2", "Employee2");
        Employee employee3 = new Employee("Employee3", "Employee3");

        Company company1 = new Company("Company1");
        Company company2 = new Company("Company2");

        employee1.getCompanies().add(company1);
        employee2.getCompanies().add(company1);
        employee3.getCompanies().add(company2);

        company1.getEmployees().add(employee1);
        company1.getEmployees().add(employee2);

        company2.getEmployees().add(employee3);

        // When
        companyDao.save(company1);
        int company1_id = company1.getId();

        companyDao.save(company2);
        int company2_id = company2.getId();

        // Then
        Assert.assertNotEquals(0, company1_id);
        Assert.assertNotEquals(0, company2_id);

        // Clean-up
        companyDao.deleteById(company1_id);
        companyDao.deleteById(company2_id);
    }
}

