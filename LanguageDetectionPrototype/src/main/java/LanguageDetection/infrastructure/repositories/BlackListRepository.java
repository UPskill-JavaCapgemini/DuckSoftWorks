package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.entities.BlackListItem;
import LanguageDetection.infrastructure.datamodel.BlackListJPA;
import LanguageDetection.infrastructure.datamodel.DataAssemblers.BlackListDomainDataAssembler;
import LanguageDetection.infrastructure.repositories.JPARepositories.BlackListJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



public class BlackListRepository {

    @Autowired
    BlackListDomainDataAssembler blackListAssembler;

    @Autowired
    BlackListJpaRepository blackListRepository;

    public BlackListItem save(BlackListItem blackList) throws MalformedURLException {
        BlackListJPA blackListJPA = blackListAssembler.toData(blackList);

        BlackListJPA savedBlackList = blackListRepository.save(blackListJPA);

        return blackListAssembler.toDomain(savedBlackList);
    }

    public List<BlackListItem> findAll() throws MalformedURLException {
        List<BlackListJPA> blackListJpaItems = (List<BlackListJPA>) blackListRepository.findAll();

        List<BlackListItem> blackListItems = new ArrayList<>();
        for (BlackListJPA blackListJPA : blackListJpaItems) {
            BlackListItem blackList = blackListAssembler.toDomain(blackListJPA);
            blackListItems.add(blackList);
        }

        return blackListItems;
    }

    public boolean isBlackListed(BlackListItem blackListUrl) {
        String url = blackListUrl.getUrl().toString();
        Optional<BlackListJPA> opBlackListJPA = blackListRepository.findByUrl(url);

        return opBlackListJPA.isPresent();
    }
}
