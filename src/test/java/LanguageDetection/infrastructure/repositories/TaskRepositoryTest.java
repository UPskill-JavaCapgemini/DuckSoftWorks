package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.Task;
import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.model.ValueObjects.TaskStatus;
import LanguageDetection.infrastructure.repositories.JPARepositories.TaskJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class TaskRepositoryTest {

    @InjectMocks
    TaskRepository repository;

    @Mock
    TaskJpaRepository jpaRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTasksShouldReturnListOf2Task() throws MalformedURLException {
        //Arrange
        Task task = mock(Task.class);
        when(jpaRepository.findAllByUserId(any(Long.class))).thenReturn(List.of(task, task));

        //Act / Assert
        Assertions.assertEquals(repository.findAllTasks(any(Long.class)), List.of(task, task));
    }

    @Test
    void findByStatusContainingShouldReturnListWithTasksThatMatchStatus() throws MalformedURLException {
        TaskStatus status = TaskStatus.Processing;
        Task task = mock(Task.class);
        when(jpaRepository.findByCurrentStatusLikeAndUserId(status, 1L)).thenReturn(List.of(task));

        //Act / Assert
        Assertions.assertEquals(repository.findByStatusContaining(status, 1L), List.of(task));
    }


    @Test
    void findByCategoryContainingShouldReturnListWithTaskThatHaveCategory2() {
        //Arrange
        Category category = new Category("Sports");
        Task task = mock(Task.class);
        when(jpaRepository.findTaskByCategoryLikeAndUserId(category, 1L)).thenReturn(List.of(task));

        //Act / Assert
        Assertions.assertEquals(repository.findByCategoryNameContaining(category, 1L), List.of(task));
    }


    @Test
    void findByStatusAndByCategoryContainingShouldReturnListOfTaskWithCategoryAndStatusThatMatch() {
        //Arrange
        TaskStatus status = TaskStatus.Processing;
        Category category = new Category("Sports");
        Task task = mock(Task.class);
        when(jpaRepository.findTaskByCategoryLikeAndCurrentStatusLikeAndUserId(category, status, 1L)).thenReturn(List.of(task));

        //Act / Assert
        Assertions.assertEquals(repository.findByStatusAndByCategoryContaining(status, category, 1L), List.of(task));
    }

    @Test
    void existsByUrlAndIsProcessingShouldReturnFalseWhenUrlInArgumentDoesNotExistOrIsNotProcessing() throws MalformedURLException {
        //Arrange
        InputUrl inputUrl = new InputUrl("https://www.w3.org/TR/PNG/iso_8859-1.txt");
        TaskStatus processing = TaskStatus.Processing;

        when(jpaRepository.existsTaskByInputUrlAndCurrentStatusAndUserId(inputUrl, processing, 1L)).thenReturn(false);

        //Act / Assert
        Assertions.assertFalse(repository.existsByUrlAndIsProcessing(inputUrl, 1L));

    }

    @Test
    void existsByUrlAndIsProcessingShouldReturnTrueWhenUrlInArgumentExistsAndIsProcessing() throws MalformedURLException {
        //Arrange
        InputUrl inputUrl = new InputUrl("https://www.w3.org/TR/PNG/iso_8859-1.txt");
        TaskStatus processing = TaskStatus.Processing;

        when(jpaRepository.existsTaskByInputUrlAndCurrentStatusAndUserId(inputUrl, processing, 1L)).thenReturn(true);

        //Act / Assert
        Assertions.assertTrue(repository.existsByUrlAndIsProcessing(inputUrl, 1L));

    }

    @Test
    void saveTaskShouldReturnSameObjectWhenSaved(){
        //Arrange
        Task task = mock(Task.class);
        when(jpaRepository.save(task)).thenReturn(task);

        //Act / Assert
        Assertions.assertEquals(repository.saveTask(task), task);
    }

    @Test
    void findTaskByIdShouldReturnOptionalEmptyWhenIdNotFound(){
        //Arrange
        when(jpaRepository.findById(1L)).thenReturn(Optional.empty());

        //Act / Assert
        Assertions.assertEquals(repository.findByTaskId(1L), Optional.empty());
    }

    @Test
    void findTaskByIdShouldReturnOptionalWithTaskWhenTaskWithCertainIdIsFound(){
        //Arrange
        Task task = mock(Task.class);
        when(jpaRepository.findById(1L)).thenReturn(Optional.of(task));

        //Act / Assert
        Assertions.assertEquals(repository.findByTaskId(1L), Optional.of(task));
    }
}
