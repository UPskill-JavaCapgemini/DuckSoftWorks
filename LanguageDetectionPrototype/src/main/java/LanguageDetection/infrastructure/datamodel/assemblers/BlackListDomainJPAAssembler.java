package LanguageDetection.infrastructure.datamodel.assemblers;

import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.entities.BlackList;
import LanguageDetection.infrastructure.datamodel.BlackListJPA;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class BlackListDomainJPAAssembler {

    public BlackListJPA toData(BlackList blackList) {
        InputUrl inputUrl = blackList.getUrl();
        String urlPath = inputUrl.getUrl();
        return new BlackListJPA(urlPath);
    }

    public BlackList toDomain(BlackListJPA blackListJPA) throws MalformedURLException {
        String input = blackListJPA.getUrl();
        URL url = new URL(input);
        InputUrl inputUrl = new InputUrl(url);
        return new BlackList(inputUrl);
    }

}
