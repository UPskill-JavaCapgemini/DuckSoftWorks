package LanguageDetection.domain.DomainService;

import LanguageDetection.domain.model.ValueObjects.BlackListUrl;
import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.model.BlackListItem;
import LanguageDetection.domain.model.IBlackListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BlackListService {


    @Autowired
    IBlackListItemRepository blackListItemRepository;

    public BlackListItem saveBlackListItem(BlackListItem blackListItem) {

        List<BlackListItem> blackListItemList = findAllBlackListItems();
        String inputUrlPath = blackListItem.getBlackListUrl().toString();

        //TODO: Verify how we can improve efficiency of this.
        for (BlackListItem blackListItemRepo : blackListItemList) {
            String blackListUrlPath = blackListItemRepo.getBlackListUrl().toString();
            if (inputUrlPath.contains(blackListUrlPath)) {

                throw new IllegalArgumentException();
            }
        }
        return blackListItemRepository.saveBlackListItem(blackListItem);
    }

    public boolean deleteByBlackListUrl(BlackListUrl blackListUrl) {
        return blackListItemRepository.deleteByBlackListUrl(blackListUrl);
    }

    /**
     * The method that verifies if an Item already exists in the repository.
     *
     * @param inputUrl instance of url to verify if it is blacklisted
     * @return boolean that defines if the url is present in the repository list or not.
     */
    public boolean isBlackListed(InputUrl inputUrl) {
        boolean isBlackListed = false;
        List<BlackListItem> blackListItemList = findAllBlackListItems();
        String inputUrlPath = inputUrl.toString();
        //TODO: Verify how we can improve efficiency of this.
        for (BlackListItem blackListItem : blackListItemList) {
            String blackListUrlPath = blackListItem.getBlackListUrl().toString();
            if (inputUrlPath.contains(blackListUrlPath)) {
                return true;
            }
        }
        return isBlackListed;
    }

    public List<BlackListItem> findAllBlackListItems() {
        return blackListItemRepository.findAllBlackListItems();
    }
}
