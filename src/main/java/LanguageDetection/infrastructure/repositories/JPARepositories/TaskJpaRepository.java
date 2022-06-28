package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.Task;
import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.model.ValueObjects.TaskStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Represents the CategoryJpaRepository. Extends the CrudRepository Interface
 */
public interface TaskJpaRepository extends CrudRepository <Task, Long> {
    Iterable<Task> findByCurrentStatusLikeAndUserIdOrderByIdDesc(TaskStatus st, Long userId);

    Iterable<Task> findTaskByCategoryLikeAndUserIdOrderByIdDesc(Category category, Long userId);

    Iterable<Task> findTaskByCategoryLikeAndCurrentStatusLikeAndUserIdOrderByIdDesc(Category category, TaskStatus status, Long userId);

    Optional<Task> findById(String name);

    Iterable<Task> findAllByUserIdOrderByIdDesc(Long userId);

    Optional<Task> findByIdAndUserId(Long id, Long userId);

    boolean existsTaskByInputUrlAndCurrentStatusAndUserId(InputUrl inputUrl,TaskStatus status,Long userId);

}
