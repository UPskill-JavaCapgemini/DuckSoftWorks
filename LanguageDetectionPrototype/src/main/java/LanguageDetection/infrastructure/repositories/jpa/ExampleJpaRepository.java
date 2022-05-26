package LanguageDetection.infrastructure.repositories.jpa;

import com.example.infrastructure.datamodel.ExampleJpa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskJpaRepository extends CrudRepository<TaskJpa, String> {
    Optional<TaskJpa> findById(Long id);
    List<TaskJpa> findByNameContaining(String name);
    List<TaskJpa> findAll();

    // Can also use a SQL query
    // don't forget: if you are using a jpa to save to the database
    // then you need to use the jpa in the query
    @Query("SELECT e FROM TaskJpa e")
    List<TaskJpa> customQuery();

}
