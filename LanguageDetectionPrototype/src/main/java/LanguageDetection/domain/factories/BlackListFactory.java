package LanguageDetection.domain.factories;

import LanguageDetection.domain.entities.BlackList;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

@Service
public class BlackListFactory implements IBlackListFactory {

    @Override
    public BlackList createBlackListItem(String url) throws MalformedURLException {
        return new BlackList(url);
    }
}
