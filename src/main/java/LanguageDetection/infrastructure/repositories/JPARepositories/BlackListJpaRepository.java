package LanguageDetection.infrastructure.repositories.JPARepositories;


import LanguageDetection.domain.ValueObjects.BlackListUrl;
import LanguageDetection.domain.entities.BlackListItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BlackListJpaRepository extends CrudRepository<BlackListItem, BlackListUrl> {

    int deleteByBlackListUrl(BlackListUrl blackListUrl);
}
