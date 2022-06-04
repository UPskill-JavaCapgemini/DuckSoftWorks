package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.infrastructure.datamodel.CategoryJpa;
import org.springframework.data.repository.CrudRepository;

public interface CategoryJpaRepository extends CrudRepository<CategoryJpa,Long> {
}
