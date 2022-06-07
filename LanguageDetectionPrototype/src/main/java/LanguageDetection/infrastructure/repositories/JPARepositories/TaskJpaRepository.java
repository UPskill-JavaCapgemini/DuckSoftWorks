package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.domain.entities.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskJpaRepository extends CrudRepository <Task, Long> {


//    Optional<Task> findById(String name);


}
