package LanguageDetection;


import LanguageDetection.application.Bootstrap.Bootstrap;
import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.application.controllers.BlackListController;
import LanguageDetection.application.services.BlackListService;
import LanguageDetection.domain.entities.BlackListItem;
import LanguageDetection.infrastructure.repositories.BlackListRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws MalformedURLException {
        SpringApplication.run(Application.class);
        


        //NewBlackListInfoDTO bl1 = new NewBlackListInfoDTO("http://127.0.0.1.");



        /*BlackListItem blackListItem = new BlackListItem("http://127.0.0.1.");
        BlackListRepository blackListRepository = new BlackListRepository();
        blackListRepository.saveBlackListItem(blackListItem);*/

        //BlackListService blackListService = new BlackListService();
        //blackListService.createAndSaveBlackListItem(bl1);

    }
}


