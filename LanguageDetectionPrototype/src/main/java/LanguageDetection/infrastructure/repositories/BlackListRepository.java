
package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.ValueObjects.BlackListUrl;
import LanguageDetection.domain.entities.BlackListItem;
import LanguageDetection.domain.entities.IBlackListItem;
import LanguageDetection.infrastructure.repositories.JPARepositories.BlackListJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Repository
public class BlackListRepository implements IBlackListItem{

    @Autowired
    BlackListJpaRepository blackListRepository;

    public BlackListItem saveBlackListItem(BlackListItem blackListItem) throws MalformedURLException {
        BlackListItem savedBlackListUrl = blackListRepository.save(blackListItem);
        return savedBlackListUrl;
    }

    @Override
    @Transactional
    public boolean deleteByBlackListUrl(BlackListItem blackListItem) {
        Optional<BlackListItem> itemToBeDeleted = blackListRepository.findById(blackListItem.identity());
        if (itemToBeDeleted.isPresent())
        {
            blackListRepository.delete(blackListItem);
            return true;
        }
        else
            return false;
    }


    public List<BlackListItem> findAllBlackListItems() throws MalformedURLException {
        List<BlackListItem> blackListItems = (List<BlackListItem>) blackListRepository.findAll();
        return blackListItems;
    }



    public boolean isBlackListed(BlackListItem blackListItem) {
        BlackListUrl blackListUrl = blackListItem.getUrl();
        Optional<BlackListItem> blackListRepoUrl = blackListRepository.findById(blackListUrl);

        return blackListRepoUrl.isPresent();
    }
}
