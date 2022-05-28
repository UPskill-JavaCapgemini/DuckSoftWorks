package LanguageDetection.application.controllers;
import LanguageDetection.application.dtos.NewTaskInfoDTO;
import LanguageDetection.application.dtos.TaskDTO;
import LanguageDetection.application.dtos.assemblers.TaskDomainDTOAssembler;
import LanguageDetection.application.services.AnalyzerService;
import LanguageDetection.application.services.DictionaryService;
import LanguageDetection.application.services.TaskService;
import LanguageDetection.domain.entities.example.Task;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
public class TaskControllerTest {


    @InjectMocks
    TaskController taskController;

    @MockBean
    private TaskService taskService;

    @MockBean
    private TaskDomainDTOAssembler taskDomainDTOAssembler;

    @MockBean
    private AnalyzerService analyzerService;

    @MockBean
    private DictionaryService directory;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldReturnObjectCreated() throws ParseException, IOException {

        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        NewTaskInfoDTO newTaskInfo = new NewTaskInfoDTO();
        newTaskInfo.setText("When is it time");
        Task task = mock(Task.class);
        TaskDTO taskDTO = taskDomainDTOAssembler.toDTO(task);

        when( taskService.createTask(newTaskInfo)).thenReturn(taskDTO);

        // Act
        ResponseEntity<Object> responseEntity = taskController.createTaskFromInput(newTaskInfo);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);
    }

}