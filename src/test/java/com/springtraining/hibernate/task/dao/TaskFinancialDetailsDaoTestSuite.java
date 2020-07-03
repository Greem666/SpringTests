package com.springtraining.hibernate.task.dao;

import com.springtraining.hibernate.task.TaskFinancialDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskFinancialDetailsDaoTestSuite {
    @Autowired
    private TaskFinancialDetailsDao taskFinancialDetailsDao;

    @Test
    public void testFindPaidBy() {
        // Given
        TaskFinancialDetails taskFinancialDetails1 = new TaskFinancialDetails(new BigDecimal("10000"), true);
        TaskFinancialDetails taskFinancialDetails2 = new TaskFinancialDetails(new BigDecimal("1000000"), false);
        TaskFinancialDetails taskFinancialDetails3 = new TaskFinancialDetails(new BigDecimal("20000"), false);

        // When
        for (TaskFinancialDetails details: Arrays.asList(taskFinancialDetails1, taskFinancialDetails2, taskFinancialDetails3)) {
            taskFinancialDetailsDao.save(details);
        }
        int id1 = taskFinancialDetails1.getId();
        int id2 = taskFinancialDetails2.getId();
        int id3 = taskFinancialDetails3.getId();

        List<TaskFinancialDetails> resultsTrue = taskFinancialDetailsDao.findByPaid(true);
        List<TaskFinancialDetails> resultsFalse = taskFinancialDetailsDao.findByPaid(false);

        // Then
        Assert.assertEquals(1, resultsTrue.size());
        Assert.assertEquals(2, resultsFalse.size());

        // Clean-up
        for (int id: Arrays.asList(id1, id2, id3)) {
            taskFinancialDetailsDao.deleteById(id);
        }
    }

}
