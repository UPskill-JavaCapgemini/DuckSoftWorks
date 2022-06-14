package LanguageDetection.domain.DomainService;

import LanguageDetection.domain.entities.BlackListItem;
import LanguageDetection.domain.entities.IBlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Component
public class BlackListService {


    @Autowired
    IBlackListRepository iBlackListRepository;

    public Optional<BlackListItem> saveBlackListItem(BlackListItem blackListItem) throws MalformedURLException {
        return iBlackListRepository.saveBlackListItem(blackListItem);
    }

    public boolean deleteByBlackListUrl(BlackListItem blackListItem) {
        return iBlackListRepository.deleteByBlackListUrl(blackListItem);
    }

    public boolean isBlackListed(BlackListItem blackList) {
        return iBlackListRepository.isBlackListed(blackList);
    }

    public List<BlackListItem> findAllBlackListItems() throws MalformedURLException {
        return iBlackListRepository.findAllBlackListItems();
    }
}
