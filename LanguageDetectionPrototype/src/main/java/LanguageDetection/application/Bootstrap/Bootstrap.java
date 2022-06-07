package LanguageDetection.application.Bootstrap;


import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.application.services.BlackListService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.MalformedURLException;

@Service
public class Bootstrap implements InitializingBean {

    @Autowired
    BlackListService blackListService;

    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {
        createAndSaveBlackListItem();
    }

    private void createAndSaveBlackListItem() throws MalformedURLException {
        NewBlackListInfoDTO blackListInfoDTO = new NewBlackListInfoDTO("http://127.0.0.1");
        blackListService.createAndSaveBlackListItem(blackListInfoDTO);
    }
}
