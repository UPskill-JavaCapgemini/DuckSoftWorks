package LanguageDetection.domain.model;

import LanguageDetection.domain.model.ValueObjects.CategoryName;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository {

    Task saveTask(Task task);

    Optional<Task> findByTaskId(Long identity);

    List<Task> findAllTasks();

    List<Task> findByStatusContaining(Task.TaskStatus st);

    List<Task> findByCategoryNameContaining(Category category);

    List<Task> findByStatusAndByCategoryContaining(Task.TaskStatus status, Category category);

//    boolean deletedById(Long id);

}
