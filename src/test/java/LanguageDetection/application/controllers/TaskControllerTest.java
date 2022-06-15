/*
package LanguageDetection.application.controllers;

import LanguageDetection.application.DTO.NewCancelThreadDTO;
import LanguageDetection.application.DTO.NewTaskInfoDTO;
import LanguageDetection.application.DTO.TaskDTO;
import LanguageDetection.application.DTO.TaskStatusDTO;
import LanguageDetection.application.services.TaskService;
import LanguageDetection.domain.entities.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskControllerTest {

    @InjectMocks
    TaskController controller;

    @Mock
    TaskService taskService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAndSaveTaskShouldReturnHTTPStatusCode201WhenTaskIsPresent() throws IOException {
        // Arrange

        NewTaskInfoDTO newTaskInfo = new NewTaskInfoDTO();
        newTaskInfo.setUrl("http://www.textexample.com/text/text.txt");

        Task task = mock(Task.class);
        Optional<TaskStatusDTO> optional = Optional.of(new TaskStatusDTO(task));
        when(taskService.createAndSaveTask(newTaskInfo)).thenReturn(optional);

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
        when(taskService.createAndSaveTask(newTaskInfo)).thenReturn(optional);

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
        when(taskService.cancelTaskAnalysis(newCancelThreadDTO)).thenReturn(optional);

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
        when(taskService.cancelTaskAnalysis(newCancelThreadDTO)).thenReturn(optional);

        // Act
        ResponseEntity<Object> responseEntity = controller.cancelAnalysisThread(newCancelThreadDTO);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 202);
    }
}*/
