package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.CategoryName;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository {

    Task saveTask(Task task);

    Optional<Task> findById(Long identity);

    List<Task> findAllTasks();

    List<Task> findByStatusContaining(Task.TaskStatus st);

    List<Task> findByCategoryNameContaining(CategoryName categoryname);

    List<Task> findByStatusAndByCategoryContaining(Task.TaskStatus status, Category category);

//    boolean deletedById(Long id);

}
