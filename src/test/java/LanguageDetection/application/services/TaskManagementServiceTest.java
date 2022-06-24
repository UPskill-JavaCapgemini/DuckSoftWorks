package LanguageDetection.application.services;

import LanguageDetection.application.DTO.*;
import LanguageDetection.application.DTO.DTOAssemblers.TaskDomainDTOAssembler;
import LanguageDetection.domain.DomainService.TaskService;
import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.Task;
import LanguageDetection.domain.model.ValueObjects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TaskManagementServiceTest {

    @InjectMocks
    TaskManagementService taskManagementService;

    @Mock
    TaskService taskService;

    @Mock
    TaskDomainDTOAssembler domainDTOAssembler;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createAndSaveTaskShouldReturnTaskStatusDTOWhenTaskIsCreated() throws MalformedURLException {
        //Arrange
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        String category = "Sports";
        int timeout = 2;

        NewTaskInfoDTO newTaskInfoDTO = new NewTaskInfoDTO(url, category, timeout);
        Task task = Mockito.mock(Task.class);

        TaskStatusDTO taskStatusDTO = new TaskStatusDTO(1L, TaskStatus.Processing);

        when(taskService.createAndSaveTask(url, timeout, category)).thenReturn(Optional.of(task));
        when(domainDTOAssembler.toDTO(task)).thenReturn(taskStatusDTO);

        //Act / Assert
        Assertions.assertEquals(taskManagementService.createAndSaveTask(newTaskInfoDTO), Optional.of(taskStatusDTO));
    }

    @Test
    void createAndSaveTaskShouldReturnOptionalEmptyWhenTaskIsNotCreated() throws MalformedURLException {
        //Arrange
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        String category = "Sports";
        int timeout = 2;

        NewTaskInfoDTO newTaskInfoDTO = new NewTaskInfoDTO(url, category, timeout);
        Task task = Mockito.mock(Task.class);

        TaskStatusDTO taskStatusDTO = new TaskStatusDTO(1L, TaskStatus.Processing);

        when(taskService.createAndSaveTask(url, timeout, category)).thenReturn(Optional.empty());

        //Act / Assert
        Assertions.assertEquals(taskManagementService.createAndSaveTask(newTaskInfoDTO), Optional.empty());
    }

    @Test
    void getAllTasksShouldReturn2TasksWhen2TaskWereCreated() throws MalformedURLException {
        Task task = Mockito.mock(Task.class);
        Date date = new Date(1L);
        InputUrl inputUrl = new InputUrl("https://www.w3.org/TR/PNG/iso_8859-1.txt");
        TaskResult taskResult = new TaskResult(Language.ENGLISH);
        TaskStatus taskStatus = TaskStatus.Concluded;
        TimeOut timeOut = new TimeOut(2);
        Category category = new Category("Sports");

        TaskDTO taskDTO = new TaskDTO(1L, date,  inputUrl, taskResult, taskStatus, timeOut, category);

        when(taskService.findAllTasks()).thenReturn(List.of(task, task));
        when(domainDTOAssembler.toCompleteDTO(task)).thenReturn(taskDTO);

        Assertions.assertEquals(taskManagementService.getAllTasks(), List.of(taskDTO, taskDTO));
    }

    @Test
    void findByStatusShouldReturnTaskDTOWithCorrespondingStatus() throws MalformedURLException {
        StatusDTO statusDTO = new StatusDTO("Concluded");
        Task task = Mockito.mock(Task.class);
        Date date = new Date(1L);
        InputUrl inputUrl = new InputUrl("https://www.w3.org/TR/PNG/iso_8859-1.txt");
        TaskResult taskResult = new TaskResult(Language.ENGLISH);
        TaskStatus taskStatus = TaskStatus.Concluded;
        TimeOut timeOut = new TimeOut(2);
        Category category = new Category("Sports");

        TaskDTO taskDTO = new TaskDTO(1L, date,  inputUrl, taskResult, taskStatus, timeOut, category);


        when(taskService.findByStatusContaining(taskStatus)).thenReturn(List.of(task));
        when(domainDTOAssembler.toCompleteDTO(task)).thenReturn(taskDTO);

        Assertions.assertEquals(taskManagementService.findByStatusContaining(statusDTO), List.of(taskDTO));
    }

    @Test
    void findByCategoryNameShouldReturnTaskDTOWithCorrespondingCategory() throws MalformedURLException {
        CategoryNameDTO categoryNameDTO = new CategoryNameDTO("Sports");

        Task task = Mockito.mock(Task.class);
        Date date = new Date(1L);
        InputUrl inputUrl = new InputUrl("https://www.w3.org/TR/PNG/iso_8859-1.txt");
        TaskResult taskResult = new TaskResult(Language.ENGLISH);
        TaskStatus taskStatus = TaskStatus.Concluded;
        TimeOut timeOut = new TimeOut(2);
        Category category = new Category("Sports");

        TaskDTO taskDTO = new TaskDTO(1L, date,  inputUrl, taskResult, taskStatus, timeOut, category);

        when(taskService.findByCategoryNameContaining(Mockito.any(Category.class))).thenReturn(List.of(task));
        when(domainDTOAssembler.toCompleteDTO(task)).thenReturn(taskDTO);

        Assertions.assertEquals(taskManagementService.findByCategoryNameContaining(categoryNameDTO), List.of(taskDTO));
    }

    @Test
    void findByStatusAndCategoryShouldReturnMatchingTasks() throws MalformedURLException {
        CategoryNameDTO categoryNameDTO = new CategoryNameDTO("Sports");
        StatusDTO statusDTO = new StatusDTO("Concluded");

        Task task = Mockito.mock(Task.class);
        Date date = new Date(1L);
        InputUrl inputUrl = new InputUrl("https://www.w3.org/TR/PNG/iso_8859-1.txt");
        TaskResult taskResult = new TaskResult(Language.ENGLISH);
        TaskStatus taskStatus = TaskStatus.Concluded;
        TimeOut timeOut = new TimeOut(2);
        Category category = new Category("Sports");

        TaskDTO taskDTO = new TaskDTO(1L, date,  inputUrl, taskResult, taskStatus, timeOut, category);

        when(taskService.findByStatusAndByCategoryContaining(Mockito.any(TaskStatus.class), Mockito.any(Category.class))).thenReturn(List.of(task));
        when(domainDTOAssembler.toCompleteDTO(task)).thenReturn(taskDTO);

        Assertions.assertEquals(taskManagementService.findByStatusContainingAndCategoryContaining(statusDTO, categoryNameDTO), List.of(taskDTO));
    }

    @Test
    void cancelTaskAnalysisShouldTaskDTOCorrespondingToWhichWasCanceled() throws MalformedURLException {
        Task task = Mockito.mock(Task.class);
        NewCancelThreadDTO cancelThreadDTO = new NewCancelThreadDTO(10L);

        Date date = new Date(1L);
        InputUrl inputUrl = new InputUrl("https://www.w3.org/TR/PNG/iso_8859-1.txt");
        TaskResult taskResult = new TaskResult(Language.ENGLISH);
        TaskStatus taskStatus = TaskStatus.Concluded;
        TimeOut timeOut = new TimeOut(2);
        Category category = new Category("Sports");

        TaskDTO taskDTO = new TaskDTO(10L, date,  inputUrl, taskResult, taskStatus, timeOut, category);

        when(taskService.cancelTaskAnalysis(10L)).thenReturn(Optional.of(task));
        when(domainDTOAssembler.toCompleteDTO(task)).thenReturn(taskDTO);

        Assertions.assertEquals(taskManagementService.cancelTaskAnalysis(cancelThreadDTO), Optional.of(taskDTO));
    }
}