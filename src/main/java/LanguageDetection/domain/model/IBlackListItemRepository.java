package LanguageDetection.domain.model;

import LanguageDetection.domain.model.ValueObjects.BlackListUrl;

import java.util.List;

public interface IBlackListItemRepository {

    BlackListItem saveBlackListItem(BlackListItem blackListItem);

    boolean deleteByBlackListUrl(BlackListUrl blackListUrl);

    List<BlackListItem> findAllBlackListItems();

}
