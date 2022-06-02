package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.infrastructure.datamodel.BlackListJPA;
import org.springframework.data.repository.CrudRepository;

public interface BlackListJpaRepository extends CrudRepository<BlackListJPA,Long> {
}
