package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.infrastructure.datamodel.BlackListJPA;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BlackListJpaRepository extends CrudRepository<BlackListJPA,Long> {

    Optional<BlackListJPA> findByUrl(String url);
}
