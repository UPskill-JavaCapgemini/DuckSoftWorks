package LanguageDetection.application.controllers;

import LanguageDetection.REST.controllers.TaskController;
import LanguageDetection.application.DTO.*;
import LanguageDetection.application.services.TaskManagementService;;
import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.ValueObjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskControllerTest {

    @InjectMocks
    TaskController controller;

    @Mock
    TaskManagementService taskManagementService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAndSaveTaskShouldReturnHTTPStatusCode201WhenTaskIsPresent() throws IOException {
        // Arrange

        NewTaskInfoDTO newTaskInfo = new NewTaskInfoDTO();
        newTaskInfo.setUrl("http://www.textexample.com/text/text.txt");


        Optional<TaskStatusDTO> optional = Optional.of(new TaskStatusDTO(1L, TaskStatus.Processing));
        when(taskManagementService.createAndSaveTask(newTaskInfo)).thenReturn(optional);

        // Act
        ResponseEntity<Object> responseEntity = controller.createAndSaveTask(newTaskInfo);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);
    }

    @Test
    void createAndSaveTaskShouldReturnHTTPStatusCode400WhenTaskNotPresent() throws IOException {
        // Arrange

        NewTaskInfoDTO newTaskInfo = new NewTaskInfoDTO();
        newTaskInfo.setUrl("http://www.textexample.com/text/text.txt");

        Optional<TaskStatusDTO> optional = Optional.empty();
        when(taskManagementService.createAndSaveTask(newTaskInfo)).thenReturn(optional);

        // Act
        ResponseEntity<Object> responseEntity = controller.createAndSaveTask(newTaskInfo);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 400);
    }

    @Test
    void cancelAnalysisThreadShouldReturnHTTPStatus404WhenTaskDTOisNotPresentAndNotCanceled() throws IOException {
        // Arrange

        NewCancelThreadDTO newCancelThreadDTO = new NewCancelThreadDTO();
        newCancelThreadDTO.setId(1L);

        Optional<TaskDTO> optional = Optional.empty();
        when(taskManagementService.cancelTaskAnalysis(newCancelThreadDTO)).thenReturn(optional);

        // Act
        ResponseEntity<Object> responseEntity = controller.cancelAnalysisThread(newCancelThreadDTO);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 404);
    }

    @Test
    void cancelAnalysisThreadShouldReturnHTTPStatus202WhenTaskDTOisPresentAndCanceled() throws IOException {
        // Arrange

        NewCancelThreadDTO newCancelThreadDTO = new NewCancelThreadDTO();
        newCancelThreadDTO.setId(1L);

        TaskDTO task = mock(TaskDTO.class);
        Optional<TaskDTO> optional = Optional.of(task);
        when(taskManagementService.cancelTaskAnalysis(newCancelThreadDTO)).thenReturn(optional);

        // Act
        ResponseEntity<Object> responseEntity = controller.cancelAnalysisThread(newCancelThreadDTO);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 202);
    }

    @Test
    void ensureGetAllTasksFilterReturnHTTPStatus200() throws MalformedURLException {
        //Arrange
        Date date = new Date(1L);
        InputUrl inputUrl = new InputUrl("https://www.w3.org/TR/PNG/iso_8859-1.txt");
        TaskResult taskResult = new TaskResult(Language.ENGLISH);
        TimeOut timeOut = new TimeOut(2);
        Category category = new Category("Sports");
        CategoryNameDTO categoryName = new CategoryNameDTO("");
        StatusDTO statusDTO = new StatusDTO("");

        TaskDTO taskDTO = new TaskDTO(1L, date, inputUrl, taskResult, TaskStatus.Concluded, timeOut, category);

        when(taskManagementService.getAllTasks()).thenReturn(List.of(taskDTO));

        //Act
        ResponseEntity<Object> responseEntity = controller.getAllTasksFilter(categoryName, statusDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }
}
