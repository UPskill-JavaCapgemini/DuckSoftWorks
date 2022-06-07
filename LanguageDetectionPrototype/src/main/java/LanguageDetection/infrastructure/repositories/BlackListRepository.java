package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.entities.BlackList;
import LanguageDetection.infrastructure.datamodel.BlackListJPA;
import LanguageDetection.infrastructure.datamodel.DataAssemblers.BlackListDomainDataAssembler;
import LanguageDetection.infrastructure.repositories.JPARepositories.BlackListJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    public List<BlackList> findAll() throws MalformedURLException {
        List<BlackListJPA> blackListJpaItems = (List<BlackListJPA>) blackListRepository.findAll();

        List<BlackList> blackListItems = new ArrayList<>();
        for (BlackListJPA blackListJPA : blackListJpaItems) {
            BlackList blackList = blackListAssembler.toDomain(blackListJPA);
            blackListItems.add(blackList);
        }

        return blackListItems;
    }

    public boolean isBlackListed(BlackList blackListUrl) {
        String url = blackListUrl.getUrl().toString();
        Optional<BlackListJPA> opBlackListJPA = blackListRepository.findByUrl(url);

        return opBlackListJPA.isPresent();
    }
}
