package LanguageDetection.domain.entities;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

public interface IBlackListItem {

    BlackListItem saveBlackListItem(BlackListItem blackListItem) throws MalformedURLException;

    Optional<BlackListItem> findByBlackListItem(BlackListItem blackListItem);

    boolean deleteByBlackListUrl(BlackListItem blackListItem);

     boolean isBlackListed(BlackListItem blackListItem);

    List<BlackListItem> findAllBlackListItems() throws MalformedURLException;

}
