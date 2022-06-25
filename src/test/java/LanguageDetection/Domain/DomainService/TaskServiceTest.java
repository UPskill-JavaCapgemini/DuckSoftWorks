package LanguageDetection.Domain.DomainService;

import LanguageDetection.domain.DomainService.ILanguageDetector;
import LanguageDetection.domain.DomainService.TaskService;
import LanguageDetection.domain.DomainService.UserDetailsDomainService;
import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.ITaskRepository;
import LanguageDetection.domain.model.Task;
import LanguageDetection.domain.model.TaskFactory;
import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.model.ValueObjects.TaskStatus;
import LanguageDetection.domain.model.ValueObjects.TimeOut;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

class TaskServiceTest {

    @InjectMocks
    TaskService taskService;

    @Mock
    ITaskRepository iTaskRepository;

    @Mock
    TaskFactory taskFactory;

    @Mock
    ILanguageDetector iLanguageDetector;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ensureTaskIsNotCreatedIfUrlIsAlreadyBeingProcessed() throws MalformedURLException {
        //Arrange
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        int timeOut = 2;
        String category = "Sports";

        try(MockedStatic<UserDetailsDomainService> utils = Mockito.mockStatic(UserDetailsDomainService.class)){
            utils.when(()-> iTaskRepository.existsByUrlAndIsProcessing(any(InputUrl.class), any(Long.class))).thenReturn(true);

            Optional<Task> opTask = taskService.createAndSaveTask(url, timeOut, category);

            assertTrue(opTask.isEmpty());
        }
    }

    /*@Test
    void ensureTaskIsCreatedWhenUrlIsNotProcessing() throws MalformedURLException {
        //Arrange
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        int timeOut = 2;
        String categoryName = "Sports";
        Category category = new Category(categoryName);

        Task task = mock(Task.class);

        try(MockedStatic<UserDetailsDomainService> utils = Mockito.mockStatic(UserDetailsDomainService.class)){
            utils.when(()-> iTaskRepository.existsByUrlAndIsProcessing(any(InputUrl.class), any(Long.class))).thenReturn(false);
            when(taskFactory.createTask(url, timeOut, categoryName)).thenReturn(Optional.of(task));
            //Error. I cannot pass a mock on method argument and then return a mock. How to solve this?
            when(iTaskRepository.saveTask(task)).thenReturn(any(Task.class));

            doNothing().when(iLanguageDetector).languageAnalysis(task);

            //Act
            Optional<Task> opTask = taskService.createAndSaveTask(url, timeOut, categoryName);

            //Assert
            assertEquals(opTask, Optional.of(any(Task.class)));
        }
    }*/

    @Test
    void ensureFindAllTaskReturnListOfTasksOfCorrespondingUser(){
        Task task = Mockito.mock(Task.class);
        try(MockedStatic<UserDetailsDomainService> utils = Mockito.mockStatic(UserDetailsDomainService.class)){
            utils.when(()-> iTaskRepository.findAllTasks(any(Long.class))).thenReturn(List.of(task));


            assertEquals(taskService.findAllTasks(), List.of(task));
        }
    }

    @Test
    void ensureFindTaskByStatusReturnListOfTasksOfCorrespondingUser(){
        Task task = Mockito.mock(Task.class);
        try(MockedStatic<UserDetailsDomainService> utils = Mockito.mockStatic(UserDetailsDomainService.class)){
            utils.when(()-> iTaskRepository.findByStatusContaining(any(TaskStatus.class),any(Long.class))).thenReturn(List.of(task));


            assertEquals(taskService.findByStatusContaining(TaskStatus.Concluded), List.of(task));
        }
    }

    @Test
    void ensureFindTaskByCategoryNameReturnListOfTasksOfCorrespondingUser(){
        Category category = new Category("Sports");
        Task task = Mockito.mock(Task.class);
        try(MockedStatic<UserDetailsDomainService> utils = Mockito.mockStatic(UserDetailsDomainService.class)){
            utils.when(()-> iTaskRepository.findByCategoryNameContaining(any(Category.class), any(Long.class))).thenReturn(List.of(task));


            assertEquals(taskService.findByCategoryNameContaining(category), List.of(task));
        }
    }

    @Test
    void ensureFindTaskByCategoryNameAndStatusReturnListOfTasksOfCorrespondingUser(){
        Category category = new Category("Sports");
        Task task = Mockito.mock(Task.class);
        try(MockedStatic<UserDetailsDomainService> utils = Mockito.mockStatic(UserDetailsDomainService.class)){
            utils.when(()-> iTaskRepository.findByStatusAndByCategoryContaining(any(TaskStatus.class), any(Category.class), any(Long.class))).thenReturn(List.of(task));


            assertEquals(taskService.findByStatusAndByCategoryContaining(TaskStatus.Concluded, category), List.of(task));
        }
    }


//    @Test
//    void ensureCreateAndSaveTaskReturnsCreatedTask() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, MalformedURLException, InstantiationException {
//        //arrange
//        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
//        int inputTimeOut = 1;
//        String inputCategory = "Sports";
//        Category category = new Category(inputCategory);
//        TimeOut timeOut = new TimeOut(inputTimeOut);
//        InputUrl inputUrl = new InputUrl(url);
//
//
//
//        Method isBeingAnalyzed = TaskService.class.getDeclaredMethod("isBeingAnalyzed", String.class);
//        isBeingAnalyzed.setAccessible(true);
//
//        Method initializeLanguageAnalysis = TaskService.class.getDeclaredMethod("initializeLanguageAnalysis", Task.class);
//        initializeLanguageAnalysis.setAccessible(true);
//
//        Constructor<Task> taskConstructor = Task.class.getDeclaredConstructor(InputUrl.class,TimeOut.class,Category.class,Long.class);
//        taskConstructor.setAccessible(true);
//        Task task = taskConstructor.newInstance(inputUrl,timeOut,category,1L);
//
//
//        Optional<Task> createdTaskOp = Optional.of(task);
//
//
//        try(MockedStatic<UserDetailsDomainService> utils = Mockito.mockStatic(UserDetailsDomainService.class)){
//            utils.when(()-> iTaskRepository.existsByUrlAndIsProcessing(any(InputUrl.class), any(Long.class))).thenReturn(false);
//                Mockito.when(taskFactory.createTask(url, inputTimeOut, inputCategory)).thenReturn(createdTaskOp);
//                Mockito.doNothing().when(initializeLanguageAnalysis.invoke(taskService,task));
//
//                //act
//                Optional<Task> savedTask = taskService.createAndSaveTask(url, inputTimeOut, inputCategory);
//
//                //assert
//                assertNotNull(savedTask);
//            }
//        }
}
