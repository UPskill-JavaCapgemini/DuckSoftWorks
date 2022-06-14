package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.domain.ValueObjects.CategoryName;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.Task;
import org.springframework.data.repository.CrudRepository;


public interface TaskJpaRepository extends CrudRepository <Task, Long> {
    Iterable<Task> findByCurrentStatusLike(Task.TaskStatus st);

    Iterable<Task> findTaskByCategoryLike(CategoryName catName);

    Iterable<Task> findTaskByCategoryLikeAndCurrentTaskStatusLike(Category category, Task.TaskStatus status);


//    Optional<Task> findById(String name);


}
