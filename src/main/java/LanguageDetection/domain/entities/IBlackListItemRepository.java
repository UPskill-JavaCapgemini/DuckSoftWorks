package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.BlackListUrl;
import LanguageDetection.domain.ValueObjects.InputUrl;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

public interface IBlackListItemRepository {

    BlackListItem saveBlackListItem(BlackListItem blackListItem);

    boolean deleteByBlackListUrl(BlackListUrl blackListUrl);

    List<BlackListItem> findAllBlackListItems();

}
