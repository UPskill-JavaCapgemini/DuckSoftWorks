package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.domain.model.ValueObjects.CategoryName;
import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface TaskJpaRepository extends CrudRepository <Task, Long> {
    Iterable<Task> findByCurrentStatusLikeAndUserId(Task.TaskStatus st, Long userId);

    Iterable<Task> findTaskByCategoryLikeAndUserId(Category category, Long userId);

    Iterable<Task> findTaskByCategoryLikeAndCurrentStatusLikeAndUserId(Category category, Task.TaskStatus status, Long userId);

    Optional<Task> findById(String name);

    Iterable<Task> findAllByUserId(Long userId);

}
