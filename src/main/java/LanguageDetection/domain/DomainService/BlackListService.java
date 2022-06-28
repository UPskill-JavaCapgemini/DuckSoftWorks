package LanguageDetection.domain.DomainService;

import LanguageDetection.domain.model.ValueObjects.BlackListUrl;
import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.model.BlackListItem;
import LanguageDetection.domain.model.IBlackListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Represents the BlackListService.The domain service containing the validation logic for BlackList related functionalities
 */
@Component
public class BlackListService {


    @Autowired
    IBlackListItemRepository blackListItemRepository;

    /**
     * This method attempts to save a BlackListItem.
     * It will save the new BlackListItem if the inputted URL does not contain
     * any BlackListItem URL previously saved
     *
     * @param blackListItem the BlackListItem to be persisted in the database
     * @throws IllegalArgumentException if the any BlackListItems on the list have a url that contains the inputted url
     * @return the saved BlackListItem if saving was successful
     */

    public BlackListItem saveBlackListItem(BlackListItem blackListItem) {

        List<BlackListItem> blackListItemList = findAllBlackListItems();
        String inputUrlPath = blackListItem.getBlackListUrl().toString();

        for (BlackListItem blackListItemRepo : blackListItemList) {
            String blackListUrlPath = blackListItemRepo.getBlackListUrl().toString();
            if (inputUrlPath.contains(blackListUrlPath)) {

                throw new IllegalArgumentException("BlackList already blocks this URL");
            }
        }
        return blackListItemRepository.saveBlackListItem(blackListItem);
    }

    /**
     * This method deletes a persisted BlackListItem if the item is persisted in the database
     * Returns a boolean to indicate if the deletion operation was successful
     *
     * @param blackListUrl the BlackListItem to be deleted in the database
     * @return true if it was successfully deleted , false if it not
     */
    public boolean deleteByBlackListUrl(BlackListUrl blackListUrl) {
        return blackListItemRepository.deleteByBlackListUrl(blackListUrl);
    }

    /**
     * This method verifies if the inputted URL is blacklisted
     *
     * @param inputUrl the InputUrl to be verified if it is blacklisted
     * @return true if it is blacklisted, false if not
     */
    public boolean isBlackListed(InputUrl inputUrl) {
        List<BlackListItem> blackListItemList = findAllBlackListItems();
        String inputUrlPath = inputUrl.toString();

        for (BlackListItem blackListItem : blackListItemList) {
            String blackListUrlPath = blackListItem.getBlackListUrl().toString();
            if (inputUrlPath.contains(blackListUrlPath)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method fetches information for all blackListItems persisted in the database and returns a list containing them if there are any
     * or an empty list if no BlackListItems were persisted in the database
     *
     * @return a BlackListItem list
     */
    public List<BlackListItem> findAllBlackListItems() {
        return blackListItemRepository.findAllBlackListItems();
    }
}
