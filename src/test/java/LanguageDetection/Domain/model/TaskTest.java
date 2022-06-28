package LanguageDetection.Domain.model;

import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.Task;
import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.model.ValueObjects.TaskResult;
import LanguageDetection.domain.model.ValueObjects.TimeOut;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ensureTaskCanGetCategoryNameFromTask() throws MalformedURLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //Arrange
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        int inputTimeOut = 1;
        String inputCategory = "Sports";
        Category category = new Category(inputCategory);
        TimeOut timeOut = new TimeOut(inputTimeOut);
        InputUrl inputUrl = new InputUrl(url);

        Constructor<Task> taskConstructor = Task.class.getDeclaredConstructor(InputUrl.class,TimeOut.class,Category.class,Long.class);
        taskConstructor.setAccessible(true);
        Task task = taskConstructor.newInstance(inputUrl,timeOut,category,1L);

        //Act
        Category taskCategory = task.getCategory();

        //Assert
        Assertions.assertEquals(taskCategory.getCategoryName().getCategoryName(), "Sports");
    }

    @Test
    void ensureTaskCanGetUserIDFromTask() throws MalformedURLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //Arrange
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        int inputTimeOut = 1;
        String inputCategory = "Sports";
        Category category = new Category(inputCategory);
        TimeOut timeOut = new TimeOut(inputTimeOut);
        InputUrl inputUrl = new InputUrl(url);

        Constructor<Task> taskConstructor = Task.class.getDeclaredConstructor(InputUrl.class,TimeOut.class,Category.class,Long.class);
        taskConstructor.setAccessible(true);
        Task task = taskConstructor.newInstance(inputUrl,timeOut,category,5L);

        //Act
        Long taskUserId = task.getUserId();

        //Assert
        Assertions.assertEquals(taskUserId, 5L);
    }

    @Test
    void ensureTaskCantGetTaskResultWhenTaskIsCreatedAndNotAnalyzed() throws MalformedURLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //Arrange
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        int inputTimeOut = 1;
        String inputCategory = "Sports";
        Category category = new Category(inputCategory);
        TimeOut timeOut = new TimeOut(inputTimeOut);
        InputUrl inputUrl = new InputUrl(url);

        Constructor<Task> taskConstructor = Task.class.getDeclaredConstructor(InputUrl.class,TimeOut.class,Category.class,Long.class);
        taskConstructor.setAccessible(true);
        Task task = taskConstructor.newInstance(inputUrl,timeOut,category,5L);

        //Act
        TaskResult taskUserId = task.getTaskResult();

        //Assert
        Assertions.assertNull(taskUserId);
    }
}