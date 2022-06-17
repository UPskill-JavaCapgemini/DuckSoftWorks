
package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.ValueObjects.BlackListUrl;
import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.entities.BlackListItem;
import LanguageDetection.domain.entities.IBlackListItemRepository;
import LanguageDetection.infrastructure.repositories.JPARepositories.BlackListJpaRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

/**
 * Represents the BlackList Repository   responsible for handling all interactions with DB of this Domain object.
 * Implements the IBlackListItemRepository interface to allow decoupling and easier transition to other DB in the future.
 */

@Repository
public class BlackListRepository implements IBlackListItemRepository {

    @Autowired
    BlackListJpaRepository blackListJpaRepository;

    public BlackListItem saveBlackListItem(BlackListItem blackListItem) {
        return blackListJpaRepository.save(blackListItem);
    }

    /**
     * Method that allows to delete a BlackList Item by its url from DB
     *
     * @param blackListUrl
     * @return true if it was sucessfully deleted
     */

    @Override
    @Transactional
    public boolean deleteByBlackListUrl(BlackListUrl blackListUrl) {
        int deleted = blackListJpaRepository.deleteByBlackListUrl(blackListUrl);
        return deleted >= 1;


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
}
