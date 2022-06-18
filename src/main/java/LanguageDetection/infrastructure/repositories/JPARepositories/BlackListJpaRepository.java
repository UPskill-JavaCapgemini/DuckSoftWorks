package LanguageDetection.infrastructure.repositories.JPARepositories;


import LanguageDetection.domain.model.ValueObjects.BlackListUrl;
import LanguageDetection.domain.model.BlackListItem;
import org.springframework.data.repository.CrudRepository;

public interface BlackListJpaRepository extends CrudRepository<BlackListItem, BlackListUrl> {

    int deleteByBlackListUrl(BlackListUrl blackListUrl);
}
