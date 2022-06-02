package LanguageDetection.application.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TaskServiceTest {

    @InjectMocks
    TaskService taskService;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
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
