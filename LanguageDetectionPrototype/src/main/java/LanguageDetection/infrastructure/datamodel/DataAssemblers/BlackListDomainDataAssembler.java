package LanguageDetection.infrastructure.datamodel.DataAssemblers;

import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.entities.BlackList;
import LanguageDetection.infrastructure.datamodel.BlackListJPA;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

@Service
public class BlackListDomainDataAssembler {


    public BlackListJPA toData(BlackList blackList) {
        InputUrl inputUrl = blackList.getUrl();
        String urlPath = inputUrl.getUrl();
        return new BlackListJPA(urlPath);
    }

    public BlackList toDomain(BlackListJPA blackListJPA) throws MalformedURLException {
        String input = blackListJPA.getUrl();
        return new BlackList(input);
    }

}

