package LanguageDetection.infrastructure.repositories.JPARepositories;


import java.util.Optional;
import java.util.List;


import LanguageDetection.domain.ValueObjects.PersonId;
import LanguageDetection.infrastructure.repositories.JPARepositories.jpa.PersonJpa;
import org.springframework.data.repository.CrudRepository;

public interface PersonJpaRepository extends CrudRepository<PersonJpa, PersonId> {

    List<PersonJpa> findAll();

    List<PersonJpa> findByLastName(String lastName);

    Optional<PersonJpa> findById(PersonId id);

    Optional<PersonJpa> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}