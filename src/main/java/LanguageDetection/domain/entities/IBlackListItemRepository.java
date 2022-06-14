package LanguageDetection.domain.entities;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

public interface IBlackListItemRepository {


    Optional<BlackListItem> saveBlackListItem(BlackListItem blackListItem);

/*
    Optional<BlackListItem> findByBlackListItem(BlackListItem blackListItem);
*/

    boolean deleteByBlackListUrl(BlackListItem blackListItem);

     boolean isBlackListed(BlackListItem blackListItem);

    List<BlackListItem> findAllBlackListItems() throws MalformedURLException;

}
