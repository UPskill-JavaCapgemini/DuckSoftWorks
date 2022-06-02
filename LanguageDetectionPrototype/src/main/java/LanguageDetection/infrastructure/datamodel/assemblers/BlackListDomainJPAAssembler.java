package LanguageDetection.infrastructure.datamodel.assemblers;

import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.entities.BlackList;
import LanguageDetection.domain.entities.Task;
import LanguageDetection.infrastructure.datamodel.BlackListJPA;
import LanguageDetection.infrastructure.datamodel.TaskJpa;
import org.springframework.stereotype.Service;

import javax.naming.MalformedLinkException;
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
