package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.entities.BlackList;
import LanguageDetection.infrastructure.datamodel.BlackListJPA;
import LanguageDetection.infrastructure.datamodel.DataAssemblers.BlackListDomainDataAssembler;
import LanguageDetection.infrastructure.repositories.JPARepositories.BlackListJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;

@Repository
public class BlackListRepository {

    @Autowired
    BlackListDomainDataAssembler blackListAssembler;

    @Autowired
    BlackListJpaRepository blackListRepository;

    public BlackList save(BlackList blackList) throws MalformedURLException {
        BlackListJPA blackListJPA = blackListAssembler.toData(blackList);

        BlackListJPA savedBlackList = blackListRepository.save(blackListJPA);

        return blackListAssembler.toDomain(savedBlackList);
    }

}
