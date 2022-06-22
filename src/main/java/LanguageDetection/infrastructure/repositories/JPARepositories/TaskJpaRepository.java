package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.Task;
import LanguageDetection.domain.model.ValueObjects.TaskStatus;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface TaskJpaRepository extends CrudRepository <Task, Long> {
    Iterable<Task> findByCurrentStatusLikeAndUserId(TaskStatus st, Long userId);

    Iterable<Task> findTaskByCategoryLikeAndUserId(Category category, Long userId);

    Iterable<Task> findTaskByCategoryLikeAndCurrentStatusLikeAndUserId(Category category, TaskStatus status, Long userId);

    Optional<Task> findById(String name);

    Iterable<Task> findAllByUserId(Long userId);

}
