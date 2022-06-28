package LanguageDetection.application.DTO.DTOAssemblers;

import LanguageDetection.application.DTO.TaskStatusDTO;
import LanguageDetection.domain.model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


class TaskDomainDTOAssemblerTest {

    @InjectMocks
    TaskDomainDTOAssembler taskDomainDTOAssembler;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ensureToDTOReturnTaskStatusDTO() {
        //Arrange
        Task task = Mockito.mock(Task.class);
        TaskStatusDTO taskStatusDTO = new TaskStatusDTO(0L, null);

        //Act / Assert
        Assertions.assertEquals(taskDomainDTOAssembler.toDTO(task), taskStatusDTO);
    }
}