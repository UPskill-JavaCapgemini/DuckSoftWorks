package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.Task;
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

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class TaskRepositoryTest {

    @InjectMocks
    TaskRepository repository;

    @Mock
    TaskJpaRepository jpaRepository;

    Task task1;
    Task task2;
    Category category1;
    Category category2;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void beforeEach() throws MalformedURLException {
        category1 = new Category("Sports");
        category2 = new Category("Economics");
        task1 = new Task("https://www.w3.org/TR/PNG/iso_8859-1.txt", 1, category1);
        task2 = new Task("https://www.w3.org/TR/PNG/iso_8859-1.txt", 1, category2);
    }

    @Test
    void findAllTasksShouldReturnListOf2Tasks() throws MalformedURLException {
        when(jpaRepository.findAll()).thenReturn(List.of(task1,task2));

        //Assert
        Assertions.assertEquals(repository.findAllTasks(), List.of(task1, task2));
    }

    @Test
    void findByStatusContainingShouldReturnListWithTasksThatMatchStatus() throws MalformedURLException {
        Task.TaskStatus status = Task.TaskStatus.Processing;
        when(jpaRepository.findByCurrentStatusLike(status)).thenReturn(List.of(task1, task2));

        //Assert
        Assertions.assertEquals(repository.findByStatusContaining(status), List.of(task1, task2));
    }

    @Test
    void findByCategoryContainingShouldReturnListWithTaskThatHaveCategory2() {
        when(repository.findByCategoryContaining(category2)).thenReturn(List.of(task2));

        Assertions.assertEquals(repository.findByCategoryContaining(category2), List.of(task2));
    }
}

    /*@Test
    void findByStatusAndByCategoryContainingShouldReturnListOfTaskWithCategoryAndStatusThatMatch() {
        when(repository.findByStatusAndByCategoryContaining(Task.TaskStatus.Processing, category1)).thenReturn(List.of(task1));

        Assertions.assertEquals(repository.findByStatusAndByCategoryContaining(Task.TaskStatus.Processing, category1), List.of(task1));
    }
}*/