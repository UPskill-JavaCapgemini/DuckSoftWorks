package LanguageDetection.application.services;

import LanguageDetection.application.controllers.TaskController;
import LanguageDetection.application.dtos.NewTaskInfoDTO;
import LanguageDetection.application.dtos.TaskDTO;
import LanguageDetection.application.dtos.assemblers.TaskDomainDTOAssembler;
import LanguageDetection.domain.entities.example.Task;
import LanguageDetection.domain.factories.TaskFactory;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TaskServiceTest {

    @InjectMocks
    TaskService taskService;
    @Mock
    AnalyzerService analyzerService;
    @Mock
    TaskFactory taskFactory;
    @Mock
    TaskDomainDTOAssembler taskDomainDTOAssembler;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateANewTaskWhenInputIsGiven() throws IOException, ParseException {

//        //Arrange
//        NewTaskInfoDTO newTaskInfoDTO = new NewTaskInfoDTO();
//        newTaskInfoDTO.setText("Sample text to be tested");
////
//
//        when(analyzerService.analyze(anyString())).thenReturn("ENGLISH");
//        when(taskFactory.createTask("cleaned up string", Task.Language.ENGLISH)).thenReturn(mock(Task.class));
//        when(taskDomainDTOAssembler.toDTO(mock(Task.class))).thenReturn()
////        //Act
//        TaskDTO newTaskDTO= taskService.createTask(newTaskInfoDTO);
////        //Assert
//        Assert.assertNotNull(taskDTO);

        NewTaskInfoDTO newTaskInfoDTO = new NewTaskInfoDTO();
        newTaskInfoDTO.setText("sampletext");
        TaskDTO taskDTO = taskService.createTask(newTaskInfoDTO);

        Assert.assertNotNull(taskDTO);

    }

    @Test
    public void shouldCleanUpAStringWithSpecialCharacters() {
        //Arrange
        String testString = "<%a# test>< ! ,string @ with no.. special? characters.";
        String cleanedUpString;
        //Act
        cleanedUpString = taskService.cleanUpInputText(testString);
        //Assert
        Assert.assertEquals(" a test string with no special characters ", cleanedUpString);
    }

    @Test
    public void shouldCleanUpAStringWithMultipleSpacing() {
        //Arrange
        String testString = " a         test         string   with    multiple    spaces       ";
        String cleanedUpString;
        //Act
        cleanedUpString = taskService.cleanUpInputText(testString);
        //Assert
        Assert.assertEquals("a test string with multiple spaces", cleanedUpString);
    }

    @Test
    public void shouldCleanUpAStringWithUpperCase() {
        //Arrange
        String testString = "A tEsT STRing WitH UpPEr CASE";
        String lowerCaseString;
        //Act
        lowerCaseString = taskService.cleanUpInputText(testString);
        //Assert
        Assert.assertEquals("a test string with upper case", lowerCaseString);
    }

}
