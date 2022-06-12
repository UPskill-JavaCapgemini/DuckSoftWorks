package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.Task;
import org.springframework.data.repository.CrudRepository;


public interface TaskJpaRepository extends CrudRepository <Task, Long> {
    Iterable<Task> findByCurrentStatusLike(Task.CurrentStatus st);

    Iterable<Task> findTaskByCategoryLike(Category catName);

    Iterable<Task> findTaskByCategoryLikeAndCurrentStatusLike(Category category, Task.CurrentStatus status);


//    Optional<Task> findById(String name);


}