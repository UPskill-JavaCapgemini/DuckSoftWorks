
package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.model.ValueObjects.BlackListUrl;
import LanguageDetection.domain.model.BlackListItem;
import LanguageDetection.domain.model.IBlackListItemRepository;
import LanguageDetection.infrastructure.repositories.JPARepositories.BlackListJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Represents the BlackListRepository. The implementation of IBlackListItemRepository.
 * Handles all interactions with the database of this domain entity
 */

@Repository
public class BlackListRepository implements IBlackListItemRepository {

    @Autowired
    BlackListJpaRepository blackListJpaRepository;

    /**
     * This method attempts to save a BlackListItem
     *
     * @param blackListItem the BlackListItem to be persisted in the database
     * @return the saved BlackListItem, if saving was successful
     */

    public BlackListItem saveBlackListItem(BlackListItem blackListItem) {
        return blackListJpaRepository.save(blackListItem);
    }


    /**
     * This method deletes a persisted BlackListItem if the item is persisted in the database
     * Returns a boolean that indicates if the deletion operation was successful
     *
     * @param blackListUrl the BlackListUrl containing the information about the BlackListItem to be deleted
     * @return true if it was sucessfully deleted, false if not
     */

    @Override
    @Transactional
    public boolean deleteByBlackListUrl(BlackListUrl blackListUrl) {
        int deleted = blackListJpaRepository.deleteByBlackListUrl(blackListUrl);
        return deleted >= 1;


    }

    /**
     * This method fetches information for all blackListItems persisted in the database and returns a list containing them if there are any
     * or an empty list if no BlackListItems were persisted in the database
     *
     * @return a BlackListItem list
     */

    public List<BlackListItem> findAllBlackListItems() {
        List<BlackListItem> blackListItems = (List<BlackListItem>) blackListJpaRepository.findAll();
        return blackListItems;
    }

    @Override
    public Long countPersistedBlackListItems() {
        Long persistedBlackListItemCount = blackListJpaRepository.count();
        return persistedBlackListItemCount;
    }
}
