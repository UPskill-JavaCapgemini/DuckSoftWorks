package LanguageDetection;


import LanguageDetection.domain.entities.BlackListItem;
import LanguageDetection.infrastructure.repositories.BlackListRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws MalformedURLException {
        SpringApplication.run(Application.class);

        BlackListItem bl1 = new BlackListItem("http://127.0.0.1.");

        bl1.save()

    }


}


