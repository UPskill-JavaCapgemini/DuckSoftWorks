package LanguageDetection.domain.entities;

import java.util.Optional;

public interface ITask {

    Task saveTask(Task task);

    Optional<Task> findById(Long identity);

//    boolean deletedById(Long id);

}
