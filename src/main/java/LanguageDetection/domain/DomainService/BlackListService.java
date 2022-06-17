package LanguageDetection.domain.DomainService;

import LanguageDetection.domain.ValueObjects.BlackListUrl;
import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.entities.BlackListItem;
import LanguageDetection.domain.entities.IBlackListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.List;

@Component
public class BlackListService {


    @Autowired
    IBlackListItemRepository blackListItemRepository;

    public BlackListItem saveBlackListItem(BlackListItem blackListItem) {
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
