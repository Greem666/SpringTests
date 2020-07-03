package com.springtraining.hibernate.task.dao;

import com.springtraining.hibernate.task.Task;
import com.springtraining.hibernate.task.TaskFinancialDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskDaoTestSuite {
    @Autowired
    private TaskDao taskDao;

    private static final String DESCRIPTION = "Learn Hibernate: The 101 of madness";

    @Test
    public void testTaskDaoSave() {
        // Given
        Task task = new Task(DESCRIPTION, 7);

        // When
        taskDao.save(task);

        // Then
        int id = task.getId();
        Optional<Task> readTask = taskDao.findById(id);
        Assert.assertTrue(readTask.isPresent());

        // CleanUp
//        taskDao.deleteById(id);
    }

    @Test
    public void testTaskDaoFindByDuration() {
        // Given
        Task task = new Task(DESCRIPTION, 7);
        int duration = task.getDuration();

        // When
        taskDao.save(task);
        List<Task> readTasks = taskDao.findByDuration(duration);
        readTasks.stream()
                .forEach(System.out::println);

        // Then
        Assert.assertTrue(readTasks.contains(task));

        // CleanUp
        int id = task.getId();
        taskDao.deleteById(id);
    }

    @Test
    public void testTaskDaoSaveWithFinancialDetails() {
        // Given
        Task task = new Task("Placeholder", 7);
        task.setTaskFinancialDetails(new TaskFinancialDetails(new BigDecimal("1000000"), false));

        // When
        taskDao.save(task);
        int id = task.getId();

        // Then
        Assert.assertNotEquals(0, id);

        // Clean-up
        taskDao.deleteById(id);
    }
}
