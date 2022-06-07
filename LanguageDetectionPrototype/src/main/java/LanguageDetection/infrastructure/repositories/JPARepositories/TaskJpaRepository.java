package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.Task;
import org.springframework.data.repository.CrudRepository;


public interface TaskJpaRepository extends CrudRepository <Task, Long> {
    Iterable<Task> findByStatusContaining(Task.CurrentStatus st);

    Iterable<Task> findByCategoryContaining(Category catName);

    Iterable<Task> findByStatusContainingAndByCategoryContaining(Task.CurrentStatus status, Category category);


//    Optional<Task> findById(String name);


}
