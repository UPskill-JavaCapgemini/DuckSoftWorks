package LanguageDetection.infrastructure.repositories.JPARepositories;


import LanguageDetection.domain.model.BlackListItem;
import LanguageDetection.domain.model.ValueObjects.BlackListUrl;
import org.springframework.data.repository.CrudRepository;

/**
 * Represents the BlackListJpaRepository. Extends the CrudRepository Interface
 */
public interface BlackListJpaRepository extends CrudRepository<BlackListItem, BlackListUrl> {

    int deleteByBlackListUrl(BlackListUrl blackListUrl);
}
