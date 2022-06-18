package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.domain.model.ValueObjects.CategoryName;
import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface TaskJpaRepository extends CrudRepository <Task, Long> {
    Iterable<Task> findByCurrentStatusLike(Task.TaskStatus st);

    Iterable<Task> findTaskByCategoryLike(CategoryName catName);

    Iterable<Task> findTaskByCategoryLikeAndCurrentStatusLike(Category category, Task.TaskStatus status);


    Optional<Task> findById(String name);


}
