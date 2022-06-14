
package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.ValueObjects.BlackListUrl;
import LanguageDetection.domain.entities.BlackListItem;
import LanguageDetection.domain.entities.IBlackListItemRepository;
import LanguageDetection.infrastructure.repositories.JPARepositories.BlackListJpaRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Represents the BlackList Repository   responsible for handling all interactions with DB of this Domain object.
 * Implements the IBlackListItemRepository interface to allow decoupling and easier transition to other DB in the future.
 */

@Repository
public class BlackListRepositoryRepository implements IBlackListItemRepository {

    @Autowired
    BlackListJpaRepository blackListJpaRepository;

    public Optional<BlackListItem> saveBlackListItem(BlackListItem blackListItem) {
        try {
            BlackListItem savedBlackListItem = blackListJpaRepository.save(blackListItem);
            return Optional.of(savedBlackListItem);
        } catch (ConstraintViolationException e) {
            return Optional.empty();
        }
    }

    /**
     * Method that allows to delete a BlackList Item by its url from DB
     *
     * @param blackListItem
     * @return true if it was sucessfully deleted
     */

    @Override
    @Transactional
    public boolean deleteByBlackListUrl(BlackListItem blackListItem) {
        try {
            blackListJpaRepository.delete(blackListItem);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: Verify what exception is thrown here
            return false;
        }

    }

    /**
     * Method that returns a list of all the BlackList Items found in the DB
     *
     * @return a list with all the BlackList Items found
     */

    public List<BlackListItem> findAllBlackListItems() {
        List<BlackListItem> blackListItems = (List<BlackListItem>) blackListJpaRepository.findAll();
        return blackListItems;
    }

    /**
     * Method that allows to check if an url is already inserted in the DB
     *
     * @param blackListItem
     * @return an Optinal of the found blackListItem
     */

    public boolean isBlackListed(BlackListItem blackListItem) {
        BlackListUrl blackListUrl = blackListItem.getBlackListUrl();
        Optional<BlackListItem> blackListRepoUrl = blackListJpaRepository.findById(blackListUrl);
        return blackListRepoUrl.isPresent();
    }
}
