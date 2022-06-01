/*
package LanguageDetection.application.controllers;
import LanguageDetection.application.dtos.AnalysisDTO;
import LanguageDetection.application.dtos.NewAnalysisInfoDTO;
import LanguageDetection.application.dtos.assemblers.AnalyzeDTOAssembler;
import LanguageDetection.domain.entities.example.AnalyzerService;
import LanguageDetection.domain.entities.example.DictionaryService;
import LanguageDetection.application.services.AnalysisService;
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
public class AnalysisControllerTest {


    @InjectMocks
    AnalysisController analysisController;

    @MockBean
    private AnalysisService analysisService;

    @MockBean
    private AnalyzeDTOAssembler analyzeDTOAssembler;

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

        NewAnalysisInfoDTO newTaskInfo = new NewAnalysisInfoDTO();
        newTaskInfo.setText("When is it time");
        Task task = mock(Task.class);
        AnalysisDTO analysisDTO = analyzeDTOAssembler.toDTO(task);

        when( analysisService.createTask(newTaskInfo)).thenReturn(analysisDTO);

        // Act
        ResponseEntity<Object> responseEntity = analysisController.createTaskFromInput(newTaskInfo);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);
    }

}*/
