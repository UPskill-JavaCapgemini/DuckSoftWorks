package LanguageDetection.domain.model;

import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.model.ValueObjects.TaskStatus;


import java.util.List;
import java.util.Optional;

public interface ITaskRepository {

    Task saveTask(Task task);

    Optional<Task> findByTaskId(Long identity);

    List<Task> findAllTasks();

    List<Task> findByStatusContaining(TaskStatus st);

    List<Task> findByCategoryNameContaining(Category category);

    List<Task> findByStatusAndByCategoryContaining(TaskStatus status, Category category);

    boolean existsByUrlAndIsProcessing(InputUrl url);

}