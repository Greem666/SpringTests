package com.springtraining.hibernate.tasklist.dao;


import com.springtraining.hibernate.task.Task;
import com.springtraining.hibernate.task.TaskFinancialDetails;
import com.springtraining.hibernate.tasklist.TaskList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskListDaoTestSuite {
    @Autowired
    private TaskListDao taskListDao;

    @Test
    public void testFindByListName() {
        // Given
        TaskList toDoTaskList = new TaskList("ToDoList", "List of things which are not started yet.");
        TaskList inProgressTaskList = new TaskList("InProgressList", "List of things which are started but not done yet.");
        TaskList doneTaskList = new TaskList("doneList", "List of things which are done.");

        // When
        for (TaskList taskList: Arrays.asList(toDoTaskList, inProgressTaskList, doneTaskList)) {
            taskListDao.save(taskList);
        }
        int toDoId = toDoTaskList.getId();
        int inProgressId = inProgressTaskList.getId();
        int doneId = doneTaskList.getId();

        // Then
        for (String listName: Arrays.asList("ToDoList", "InProgressList", "doneList")) {
            Assert.assertEquals(1, taskListDao.findByListName(listName).size());
        }

        // CleanUp
        for (int id: Arrays.asList(toDoId, inProgressId, doneId)) {
            taskListDao.deleteById(id);
        }
    }

    @Test
    public void testTaskListDaoSaveWithTasks() {
        // Given
        Task task1 = new Task("PH1", 10);
        Task task2 = new Task("PH1", 10);

        TaskFinancialDetails tfd1 = new TaskFinancialDetails(new BigDecimal("10233"), true);
        TaskFinancialDetails tfd2 = new TaskFinancialDetails(new BigDecimal("999"), false);
        task1.setTaskFinancialDetails(tfd1);
        task2.setTaskFinancialDetails(tfd2);

        TaskList taskList = new TaskList("ToDoList", "List of things not yet done");
        taskList.getTasks().add(task1);
        taskList.getTasks().add(task2);

        task1.setTaskList(taskList);
        task2.setTaskList(taskList);

        // When
        taskListDao.save(taskList);
        int id = taskList.getId();

        // Then
        Assert.assertNotEquals(0, id);

        // Clean-up
        taskListDao.deleteById(id);

    }
}
