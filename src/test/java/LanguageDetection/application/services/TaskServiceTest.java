//package LanguageDetection.application.services;
//
//import LanguageDetection.application.DTO.*;
//import LanguageDetection.application.DTO.DTOAssemblers.TaskDomainDTOAssembler;
//import LanguageDetection.domain.ValueObjects.InputUrl;
//import LanguageDetection.domain.ValueObjects.TimeOut;
//import LanguageDetection.domain.entities.Category;
//import LanguageDetection.domain.entities.ITask;
//import LanguageDetection.domain.entities.Task;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
//public class TaskServiceTest {
//
//    @InjectMocks
//    TaskService taskService;
//
//    @Mock
//    TaskDomainDTOAssembler taskDomainDTOAssembler;
//
//    @Mock
//    BlackListService blService;
//
//    @Mock
//    CategoryService categoryService;
//
//    @Mock
//    ITask iTask;
//
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
////    @Test
////    public void shouldCleanUpAStringWithSpecialCharacters() {
////        //Arrange
////        String testString = "<%a# test>< ! ,string @ with no.. special? characters.";
////        String cleanedUpString;
////        //Act
////        cleanedUpString = taskService.cleanUpInputText(testString);
////        //Assert
////        Assert.assertEquals(" a test string with no special characters ", cleanedUpString);
////    }
////
////    @Test
////    public void shouldCleanUpAStringWithMultipleSpacing() {
////        //Arrange
////        String testString = " a         test         string   with    multiple    spaces       ";
////        String cleanedUpString;
////        //Act
////        cleanedUpString = taskService.cleanUpInputText(testString);
////        //Assert
////        Assert.assertEquals("a test string with multiple spaces", cleanedUpString);
////    }
////
////    @Test
////    public void shouldCleanUpAStringWithUpperCase() {
////        //Arrange
////        String testString = "A tEsT STRing WitH UpPEr CASE";
////        String lowerCaseString;
////        //Act
////        lowerCaseString = taskService.cleanUpInputText(testString);
////        //Assert
////        Assert.assertEquals("a test string with upper case", lowerCaseString);
////    }
//
//    @Test
//    public void shouldReturnCancelledTaskByUserCancelInput() throws MalformedURLException {
//        // Arrange
//        InputUrl inputUrl = new InputUrl("http://www.textexample.com/text/text.txt");
//        TimeOut inputTimeout = new TimeOut(2);
//        Category inputCategory = new Category("sports");
//        Task testableTask = new Task(inputUrl.getUrl(),inputTimeout.getTimeOut(),inputCategory);
//        Optional<Task> opTestableTask = Optional.of(testableTask);
//
//        TaskDTO taskDto = new TaskDTO(1L,
//                testableTask.getDate(),
//                testableTask.getInputUrl(),
//                testableTask.getLanguage(),
//                Task.TaskStatus.Canceled,
//                testableTask.getTimeOut(),
//                testableTask.getCategory());
//
//        NewCancelThreadDTO cancelThreadDTO = new NewCancelThreadDTO(1L);
//
//        when(iTask.findById(cancelThreadDTO.getId())).thenReturn(opTestableTask);
//        when(taskDomainDTOAssembler.toCompleteDTO(testableTask)).thenReturn(taskDto);
//
//        // Act
//        Optional<TaskDTO> returnedCancelledTaskDTO = taskService.cancelTaskAnalysis(new NewCancelThreadDTO(1L));
//        String cancelledDTO = returnedCancelledTaskDTO.get().toString();
//
//        // Assert
//        Mockito.verify(iTask,times(1)).findById(cancelThreadDTO.getId());
//        Mockito.verify(iTask,times(1)).saveTask(Mockito.any());
//        Mockito.verify(taskDomainDTOAssembler,times(1)).toCompleteDTO(testableTask);
//        Assert.assertTrue(cancelledDTO.contains("Canceled"));
//
//    }
//
//    //TODO test missing for a valid createAndSaveTask
//
//    @org.junit.jupiter.api.Test
//    void createAndSaveTaskShouldReturnOptionalEmptyIfCategoryExistsAndUrlIsBlackListed() throws IOException {
//        NewTaskInfoDTO infoDTO = new NewTaskInfoDTO("http://www.textexample.com/text/text.txt", "Sports", 2);
//        NewBlackListInfoDTO blackListInfoDTO = new NewBlackListInfoDTO("http://www.textexample.com/text/text.txt");
//        Category category = new Category("Sports");
//
//        when(blService.isBlackListed(blackListInfoDTO)).thenReturn(true);
//        when(categoryService.findById(category)).thenReturn(Optional.of(category));
//
//        Assertions.assertEquals(taskService.createAndSaveTask(infoDTO), Optional.empty());
//    }
//
//    @org.junit.jupiter.api.Test
//    void createAndSaveTaskShouldReturnOptionalEmptyIfCategoryDoesNotExistAndUrlIsNotBlackListed() throws IOException {
//        NewTaskInfoDTO infoDTO = new NewTaskInfoDTO("http://www.textexample.com/text/text.txt", "Sports", 2);
//        NewBlackListInfoDTO blackListInfoDTO = new NewBlackListInfoDTO("http://www.textexample.com/text/text.txt");
//        Category category = new Category("Economy");
//
//        when(blService.isBlackListed(blackListInfoDTO)).thenReturn(false);
//        when(categoryService.findById(category)).thenReturn(Optional.of(category));
//
//        Assertions.assertEquals(taskService.createAndSaveTask(infoDTO), Optional.empty());
//    }
//
//    @org.junit.jupiter.api.Test
//    void createAndSaveTaskShouldReturnOptionalEmptyIfCategoryDoesNotExistAndUrlIsBlackListed() throws IOException {
//        NewTaskInfoDTO infoDTO = new NewTaskInfoDTO("http://www.textexample.com/text/text.txt", "Sports", 2);
//        NewBlackListInfoDTO blackListInfoDTO = new NewBlackListInfoDTO("http://www.textexample.com/text/text.txt");
//        Category category = new Category("Sports");
//
//        when(blService.isBlackListed(blackListInfoDTO)).thenReturn(true);
//        when(categoryService.findById(category)).thenReturn(Optional.of(category));
//
//        Assertions.assertEquals(taskService.createAndSaveTask(infoDTO), Optional.empty());
//    }
//
//    @org.junit.jupiter.api.Test
//    void findAllTasksShouldReturnListOfAllTasks() throws MalformedURLException {
//        //Arrange
//        Category category1 = new Category("Sports");
//        Category category2 = new Category("Economics");
//        Task task1 = new Task("https://www.w3.org/TR/PNG/iso_8859-1.txt", 1, category1);
//        Task task2 = new Task("https://www.w3.org/TR/PNG/iso_8859-1.txt", 1, category2);
//        InputUrl url1 = new InputUrl("https://www.w3.org/TR/PNG/iso_8859-1.txt");
//        TimeOut timeOut = new TimeOut(1);
//        java.util.Date date = new java.util.Date(2);
//        TaskDTO taskDTO1 = new TaskDTO(1L, date, url1, Task.Language.ENGLISH, Task.TaskStatus.Processing, timeOut, category1);
//        TaskDTO taskDTO2 = new TaskDTO(1L, date, url1, Task.Language.ENGLISH, Task.TaskStatus.Processing, timeOut, category2);
//
//        when(iTask.findAllTasks()).thenReturn(List.of(task1, task2));
//        when(taskDomainDTOAssembler.toCompleteDTO(task1)).thenReturn(taskDTO1);
//        when(taskDomainDTOAssembler.toCompleteDTO(task2)).thenReturn(taskDTO2);
//
//        //Assert
//        Assert.assertEquals(taskService.getAllTasks(), List.of(taskDTO1, taskDTO2));
//    }
//
//    @org.junit.jupiter.api.Test
//    void findByStatusContainingShouldReturnListOfTaskWhichHaveMatchingStatusOnArgument() throws MalformedURLException {
//        //Arrange
//        Category category1 = new Category("Sports");
//        Task task1 = new Task("https://www.w3.org/TR/PNG/iso_8859-1.txt", 1, category1);
//        InputUrl url1 = new InputUrl("https://www.w3.org/TR/PNG/iso_8859-1.txt");
//        TimeOut timeOut = new TimeOut(1);
//        java.util.Date date = new java.util.Date(2);
//        TaskDTO taskDTO1 = new TaskDTO(1L, date, url1, Task.Language.ENGLISH, Task.TaskStatus.Processing, timeOut, category1);
//
//        StatusDTO statusDTO = new StatusDTO("Processing");
//
//        when(iTask.findByStatusContaining(Task.TaskStatus.valueOf(statusDTO.getStatus()))).thenReturn(List.of(task1));
//        when(taskDomainDTOAssembler.toCompleteDTO(task1)).thenReturn(taskDTO1);
//
//        List<TaskDTO> tasks = taskService.findByStatusContaining(statusDTO);
//        //Assert
//        Assertions.assertEquals(tasks, List.of(taskDTO1));
//    }
//
//    @org.junit.jupiter.api.Test
//    void findByCategoryContainingShouldReturnListOfTaskWhichHaveMatchingCategoryOnArgument() throws MalformedURLException {
//        //Arrange
//        CategoryNameDTO catName = new CategoryNameDTO("Sports");
//        Category category1 = new Category(catName.getCategoryName());
//        Task task1 = new Task("https://www.w3.org/TR/PNG/iso_8859-1.txt", 1, category1);
//        InputUrl url1 = new InputUrl("https://www.w3.org/TR/PNG/iso_8859-1.txt");
//        TimeOut timeOut = new TimeOut(1);
//        java.util.Date date = new java.util.Date(2);
//        TaskDTO taskDTO1 = new TaskDTO(1L, date, url1, Task.Language.ENGLISH, Task.TaskStatus.Processing, timeOut, category1);
//
//        Mockito.when(iTask.findByCategoryContaining(Mockito.any())).thenReturn(List.of(task1));
//        Mockito.when(taskDomainDTOAssembler.toCompleteDTO(task1)).thenReturn(taskDTO1);
//
//        List<TaskDTO> tasks = taskService.findByCategoryContaining(catName);
//        //Assert
//        Assertions.assertEquals(tasks, List.of(taskDTO1));
//    }
//
//    @org.junit.jupiter.api.Test
//    void findByStatusContainingAndCategoryContaining() throws MalformedURLException {
//        //Arrange
//        CategoryNameDTO categoryInfoDTO = new CategoryNameDTO("Sports");
//        Category category1 = new Category(categoryInfoDTO.getCategoryName());
//        Task task1 = new Task("https://www.w3.org/TR/PNG/iso_8859-1.txt", 1, category1);
//        InputUrl url1 = new InputUrl("https://www.w3.org/TR/PNG/iso_8859-1.txt");
//        TimeOut timeOut = new TimeOut(1);
//        java.util.Date date = new java.util.Date(2);
//        TaskDTO taskDTO1 = new TaskDTO(1L, date, url1, Task.Language.ENGLISH, Task.TaskStatus.Processing, timeOut, category1);
//
//        StatusDTO statusDTO = new StatusDTO("Processing");
//
//        when(iTask.findByStatusAndByCategoryContaining(any(), any())).thenReturn(List.of(task1));
//        when(taskDomainDTOAssembler.toCompleteDTO(task1)).thenReturn(taskDTO1);
//
//        List<TaskDTO> tasks = taskService.findByStatusContainingAndCategoryContaining(statusDTO, categoryInfoDTO);
//        //Assert
//        Assertions.assertEquals(tasks, List.of(taskDTO1));
//    }
//}