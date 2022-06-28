package LanguageDetection;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.net.MalformedURLException;

@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
public class Application {

    public static void main(String[] args) throws MalformedURLException {
        SpringApplication.run(Application.class);

    }
}


