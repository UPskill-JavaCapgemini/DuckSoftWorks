package LanguageDetection.domain.entities;

import java.util.List;
import java.util.Optional;

public interface ITask {

    Task saveTask(Task task);

    Optional<Task> findById(Long identity);

    List<Task> findAllTasks();

    List<Task> findByStatusContaining(Task.TaskStatus st);

    List<Task> findByCategoryContaining(Category category);

    List<Task> findByStatusAndByCategoryContaining(Task.TaskStatus status, Category category);

//    boolean deletedById(Long id);

}
