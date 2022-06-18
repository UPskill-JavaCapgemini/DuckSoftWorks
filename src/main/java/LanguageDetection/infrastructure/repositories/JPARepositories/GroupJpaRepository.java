package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.domain.ValueObjects.GroupId;
import LanguageDetection.infrastructure.repositories.JPARepositories.jpa.GroupJpa;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface GroupJpaRepository extends CrudRepository<GroupJpa, GroupId> {

    List<GroupJpa> findAll();

    List<GroupJpa> findByName(String lastName);

    Optional<GroupJpa> findById(GroupId id);
}