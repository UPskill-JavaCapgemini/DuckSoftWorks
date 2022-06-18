package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.domain.ValueObjects.PersonId;
import LanguageDetection.infrastructure.repositories.JPARepositories.jpa.AddressJpa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressJpaRepository extends CrudRepository<AddressJpa, Long> {

    AddressJpa findById(long id);

    List<AddressJpa> findAllByPersonId(PersonId id);

    List<AddressJpa> findAll();
}
