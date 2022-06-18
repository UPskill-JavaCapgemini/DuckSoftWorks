package LanguageDetection;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.MalformedURLException;

@SpringBootApplication
@EnableAsync
public class Application {

    public static void main(String[] args) throws MalformedURLException {
        SpringApplication.run(Application.class);

    }
}


