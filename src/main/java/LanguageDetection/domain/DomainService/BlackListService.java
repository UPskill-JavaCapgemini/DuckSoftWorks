package LanguageDetection.domain.DomainService;

import LanguageDetection.domain.entities.BlackListItem;
import LanguageDetection.domain.entities.IBlackListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BlackListService {


    @Autowired
    IBlackListItemRepository blackListItemRepository;

    public Optional<BlackListItem> saveBlackListItem(BlackListItem blackListItem) {
        return blackListItemRepository.saveBlackListItem(blackListItem);
    }

    public boolean deleteByBlackListUrl(BlackListItem blackListItem) {
        return blackListItemRepository.deleteByBlackListUrl(blackListItem);
    }

    /**
     * The method that verifies if an Item already exists in the repository.
     *
     * @param blackList instance of BlackListItem to verify in repository
     * @return boolean that defines if the url is present in the repository list or not.
     */
    public boolean isBlackListed(BlackListItem blackList) {
        return blackListItemRepository.isBlackListed(blackList);
    }

    public List<BlackListItem> findAllBlackListItems() {
        return blackListItemRepository.findAllBlackListItems();
    }
}
