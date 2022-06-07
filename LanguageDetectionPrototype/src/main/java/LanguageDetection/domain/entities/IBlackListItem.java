package LanguageDetection.domain.entities;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.net.MalformedURLException;
import java.util.List;

public interface IBlackListItem {

    BlackListItem saveBlackListItem(BlackListItem blackListItem) throws MalformedURLException;

    @Modifying
    @Query(value = "DELETE FROM BlackListItem bl WHERE bl.blacklisturl = ?1",nativeQuery = true)
    boolean deleteByBlackListUrl(BlackListItem blackListItem);

     boolean isBlackListed(BlackListItem blackListItem);

    List<BlackListItem> findAllBlackListItems() throws MalformedURLException;

}
