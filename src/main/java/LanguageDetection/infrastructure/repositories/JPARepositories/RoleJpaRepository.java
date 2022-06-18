package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.domain.ValueObjects.ERole;
import LanguageDetection.infrastructure.repositories.JPARepositories.jpa.RoleJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleJpa, Long> {
    Optional<RoleJpa> findByName(ERole name);
}