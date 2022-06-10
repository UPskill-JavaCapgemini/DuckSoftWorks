package LanguageDetection.application.services;

import LanguageDetection.application.DTO.DTOAssemblers.TaskDomainDTOAssembler;
import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.application.DTO.NewTaskInfoDTO;
import LanguageDetection.application.DTO.TaskDTO;
import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.Task;
import LanguageDetection.infrastructure.repositories.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.io.IOUtil;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TaskServiceTest {

    @InjectMocks
    TaskService taskService;

    /*@Mock
    TaskDomainDTOAssembler taskDomainDTOAssembler;*/

    @Mock
    TaskRepository taskRepository;

    @Mock
    BlackListService blService;

    @Mock
    CategoryService categoryService;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void shouldCleanUpAStringWithSpecialCharacters() {
//        //Arrange
//        String testString = "<%a# test>< ! ,string @ with no.. special? characters.";
//        String cleanedUpString;
//        //Act
//        cleanedUpString = taskService.cleanUpInputText(testString);
//        //Assert
//        Assert.assertEquals(" a test string with no special characters ", cleanedUpString);
//    }
//
//    @Test
//    public void shouldCleanUpAStringWithMultipleSpacing() {
//        //Arrange
//        String testString = " a         test         string   with    multiple    spaces       ";
//        String cleanedUpString;
//        //Act
//        cleanedUpString = taskService.cleanUpInputText(testString);
//        //Assert
//        Assert.assertEquals("a test string with multiple spaces", cleanedUpString);
//    }
//
//    @Test
//    public void shouldCleanUpAStringWithUpperCase() {
//        //Arrange
//        String testString = "A tEsT STRing WitH UpPEr CASE";
//        String lowerCaseString;
//        //Act
//        lowerCaseString = taskService.cleanUpInputText(testString);
//        //Assert
//        Assert.assertEquals("a test string with upper case", lowerCaseString);
//    }

    @Test
    public void shouldReturnCancelledTaskByUserCancelInput() throws MalformedURLException {
        // Arrange
        InputUrl inputUrl = new InputUrl("http://www.textexample.com/text/text.txt");
        TimeOut inputTimeout = new TimeOut(2);
        Category inputCategory = new Category("sports");
        Task testableTask = new Task(inputUrl.getUrl(),inputTimeout.getTimeOut(),inputCategory);
        Optional<Task> opTestableTask = Optional.of(testableTask);
        Task cancelledTask = new Task(opTestableTask.get());

        /*// Act
        when(taskRepository.findById(testableTask.getId())).thenReturn(opTestableTask);
        when(taskRepository.saveTask(opTestableTask.get())).thenReturn(cancelledTask);*/

        // Assert
        Assert.assertTrue(cancelledTask.toString().contains("Canceled"));
    }

    @org.junit.jupiter.api.Test
    void createAndSaveTaskShouldReturnOptionalEmptyIfCategoryExistsAndUrlIsBlackListed() throws IOException {
        NewTaskInfoDTO infoDTO = new NewTaskInfoDTO("http://www.textexample.com/text/text.txt", "Sports", 2);
        NewBlackListInfoDTO blackListInfoDTO = new NewBlackListInfoDTO("http://www.textexample.com/text/text.txt");
        Category category = new Category("Sports");

        when(blService.isBlackListed(blackListInfoDTO)).thenReturn(true);
        when(categoryService.findById(category)).thenReturn(Optional.of(category));

        Assertions.assertEquals(taskService.createAndSaveTask(infoDTO), Optional.empty());
    }

    @org.junit.jupiter.api.Test
    void createAndSaveTaskShouldReturnOptionalEmptyIfCategoryDoesNotExistAndUrlIsNotBlackListed() throws IOException {
        NewTaskInfoDTO infoDTO = new NewTaskInfoDTO("http://www.textexample.com/text/text.txt", "Sports", 2);
        NewBlackListInfoDTO blackListInfoDTO = new NewBlackListInfoDTO("http://www.textexample.com/text/text.txt");
        Category category = new Category("Economy");

        when(blService.isBlackListed(blackListInfoDTO)).thenReturn(false);
        when(categoryService.findById(category)).thenReturn(Optional.of(category));

        Assertions.assertEquals(taskService.createAndSaveTask(infoDTO), Optional.empty());
    }

    @org.junit.jupiter.api.Test
    void createAndSaveTaskShouldReturnOptionalEmptyIfCategoryDoesNotExistAndUrlIsBlackListed() throws IOException {
        NewTaskInfoDTO infoDTO = new NewTaskInfoDTO("http://www.textexample.com/text/text.txt", "Sports", 2);
        NewBlackListInfoDTO blackListInfoDTO = new NewBlackListInfoDTO("http://www.textexample.com/text/text.txt");
        Category category = new Category("Sports");

        when(blService.isBlackListed(blackListInfoDTO)).thenReturn(true);
        when(categoryService.findById(category)).thenReturn(Optional.of(category));

        Assertions.assertEquals(taskService.createAndSaveTask(infoDTO), Optional.empty());
    }

    @org.junit.jupiter.api.Test
    void findAllTasks() {
    }

    @org.junit.jupiter.api.Test
    void findByStatusContaining() {
    }

    @org.junit.jupiter.api.Test
    void findByCategoryContaining() {
    }

    @org.junit.jupiter.api.Test
    void findByStatusContainingAndCategoryContaining() {
    }
}
