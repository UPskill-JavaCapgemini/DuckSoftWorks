package LanguageDetection.Domain.model;

import LanguageDetection.domain.DomainService.BlackListService;
import LanguageDetection.domain.DomainService.CategoryService;
import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.Task;
import LanguageDetection.domain.model.TaskFactory;
import LanguageDetection.domain.model.ValueObjects.CategoryName;
import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.model.ValueObjects.Language;
import LanguageDetection.domain.model.ValueObjects.TaskResult;
import LanguageDetection.domain.util.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.net.MalformedURLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class TaskFactoryTest {

    @InjectMocks
    TaskFactory taskFactory;

    @Mock
    BlackListService blackListService;

    @Mock
    CategoryService categoryService;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ensureCreateTaskReturnANotNullObject() throws MalformedURLException {
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        Category category = new Category("Sports");
        Optional<Category> opCategory = Optional.of(category);
        Mockito.when(categoryService.findCategoryByName(any(CategoryName.class))).thenReturn(opCategory);
        Mockito.when(blackListService.isBlackListed(any(InputUrl.class))).thenReturn(false);
        try(MockedStatic<Utils> utils = Mockito.mockStatic(Utils.class)){
            utils.when(()-> Utils.getUserNameId()).thenReturn(1L);
            assertEquals(Utils.getUserNameId(), 1L);

            Optional<Task> opTask = taskFactory.createTask(url, 2, "Sports");

            assertNotNull(opTask.get());
        }
    }

    @Test
    void ensureCreateTaskReturnCurrentStatusAsProcessing() throws MalformedURLException {
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        Category category = new Category("Sports");
        Optional<Category> opCategory = Optional.of(category);
        Mockito.when(categoryService.findCategoryByName(any(CategoryName.class))).thenReturn(opCategory);
        Mockito.when(blackListService.isBlackListed(any(InputUrl.class))).thenReturn(false);
        try(MockedStatic<Utils> utils = Mockito.mockStatic(Utils.class)){
            utils.when(()-> Utils.getUserNameId()).thenReturn(1L);
            assertEquals(Utils.getUserNameId(), 1L);

            Optional<Task> opTask = taskFactory.createTask(url, 2, "Sports");

            assertEquals(opTask.get().getCurrentStatus(), Task.TaskStatus.Processing);
        }
    }

    //TODO: Does this tests are supposed to be here?
    @Test
    void ensureThatCancelTaskChangeStatusToCanceledWhenTaskIsProcessing() throws MalformedURLException {
        //Arrange
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        Category category = new Category("Sports");
        Optional<Category> opCategory = Optional.of(category);
        Mockito.when(categoryService.findCategoryByName(any(CategoryName.class))).thenReturn(opCategory);
        Mockito.when(blackListService.isBlackListed(any(InputUrl.class))).thenReturn(false);
        try(MockedStatic<Utils> utils = Mockito.mockStatic(Utils.class)){
            utils.when(()-> Utils.getUserNameId()).thenReturn(1L);
            assertEquals(Utils.getUserNameId(), 1L);

            Optional<Task> opTask = taskFactory.createTask(url, 2, "Sports");
            //Act
            opTask.get().cancelTask();

            //Assert
            assertEquals(opTask.get().getCurrentStatus(), Task.TaskStatus.Canceled);
        }
    }

    @Test
    void ensureThatCancelTaskDoesNotChangeStatusWhenTaskIsAlreadyConcluded() throws MalformedURLException {
        //Arrange
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        Category category = new Category("Sports");
        Optional<Category> opCategory = Optional.of(category);
        Mockito.when(categoryService.findCategoryByName(any(CategoryName.class))).thenReturn(opCategory);
        Mockito.when(blackListService.isBlackListed(any(InputUrl.class))).thenReturn(false);
        try(MockedStatic<Utils> utils = Mockito.mockStatic(Utils.class)){
            utils.when(()-> Utils.getUserNameId()).thenReturn(1L);
            assertEquals(Utils.getUserNameId(), 1L);

            Optional<Task> opTask = taskFactory.createTask(url, 2, "Sports");
            TaskResult taskResult = new TaskResult(Language.PORTUGUESE);
            opTask.get().concludeTask(taskResult);

            //Act
            opTask.get().cancelTask();

            //Assert
            assertEquals(opTask.get().getCurrentStatus(), Task.TaskStatus.Concluded);
        }
    }

    @Test
    void ensureThatConcludeTaskDoesNotChangeStatusWhenTaskIsAlreadyCanceled() throws MalformedURLException {
        //Arrange
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        Category category = new Category("Sports");
        Optional<Category> opCategory = Optional.of(category);
        Mockito.when(categoryService.findCategoryByName(any(CategoryName.class))).thenReturn(opCategory);
        Mockito.when(blackListService.isBlackListed(any(InputUrl.class))).thenReturn(false);
        try(MockedStatic<Utils> utils = Mockito.mockStatic(Utils.class)){
            utils.when(()-> Utils.getUserNameId()).thenReturn(1L);
            assertEquals(Utils.getUserNameId(), 1L);

            Optional<Task> opTask = taskFactory.createTask(url, 2, "Sports");
            opTask.get().cancelTask();

            //Act
            TaskResult taskResult = new TaskResult(Language.PORTUGUESE);
            opTask.get().concludeTask(taskResult);

            //Assert
            assertEquals(opTask.get().getCurrentStatus(), Task.TaskStatus.Canceled);
        }
    }

    @Test
    void ensureThatConcludeTaskChangeStatusWhenTaskStatusIsProcessing() throws MalformedURLException {
        //Arrange
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        Category category = new Category("Sports");
        Optional<Category> opCategory = Optional.of(category);
        Mockito.when(categoryService.findCategoryByName(any(CategoryName.class))).thenReturn(opCategory);
        Mockito.when(blackListService.isBlackListed(any(InputUrl.class))).thenReturn(false);
        try(MockedStatic<Utils> utils = Mockito.mockStatic(Utils.class)){
            utils.when(()-> Utils.getUserNameId()).thenReturn(1L);
            assertEquals(Utils.getUserNameId(), 1L);

            Optional<Task> opTask = taskFactory.createTask(url, 2, "Sports");

            //Act
            TaskResult taskResult = new TaskResult(Language.PORTUGUESE);
            opTask.get().concludeTask(taskResult);

            //Assert
            assertEquals(opTask.get().getCurrentStatus(), Task.TaskStatus.Concluded);
        }
    }


}