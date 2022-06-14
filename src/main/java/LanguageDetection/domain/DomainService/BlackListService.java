package LanguageDetection.domain.DomainService;

import LanguageDetection.domain.entities.BlackListItem;
import LanguageDetection.domain.entities.IBlackListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Component
public class BlackListService {


    @Autowired
    IBlackListItemRepository blackListItemRepository;

    public Optional<BlackListItem> saveBlackListItem(BlackListItem blackListItem) {
        return blackListItemRepository.saveBlackListItem(blackListItem);
    }

    public boolean deleteByBlackListUrl(BlackListItem blackListItem) {
        return blackListItemRepository.deleteByBlackListUrl(blackListItem);
    }

    public boolean isBlackListed(BlackListItem blackList) {
        return blackListItemRepository.isBlackListed(blackList);
    }

    public List<BlackListItem> findAllBlackListItems() throws MalformedURLException {
        return blackListItemRepository.findAllBlackListItems();
    }
}
