package LanguageDetection.domain.model;

import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.model.ValueObjects.TaskStatus;


import java.util.List;
import java.util.Optional;

/**
 * Represents the ITaskRepository
 */
public interface ITaskRepository {

    Task saveTask(Task task);

    Optional<Task> findByTaskId(Long identity);

    List<Task> findAllTasks(long userId);

    List<Task> findByStatusContaining(TaskStatus st, long userId);

    List<Task> findByCategoryNameContaining(Category category, long userId);

    List<Task> findByStatusAndByCategoryContaining(TaskStatus status, Category category, long userId);

    boolean existsByUrlAndIsProcessing(InputUrl url, long userId);

}