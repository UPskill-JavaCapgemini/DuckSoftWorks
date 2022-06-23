package LanguageDetection.Domain.DomainService;

import LanguageDetection.domain.DomainService.ILanguageDetector;
import LanguageDetection.domain.DomainService.TaskService;
import LanguageDetection.domain.DomainService.UserDetailsDomainService;
import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.ITaskRepository;
import LanguageDetection.domain.model.Task;
import LanguageDetection.domain.model.TaskFactory;
import LanguageDetection.domain.model.ValueObjects.InputUrl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.net.MalformedURLException;
import java.util.Optional;

import static LanguageDetection.domain.DomainService.UserDetailsDomainService.getUserNameId;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

}
