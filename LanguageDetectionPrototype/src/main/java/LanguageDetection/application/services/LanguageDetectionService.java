package LanguageDetection.application.services;

import LanguageDetection.domain.DomainService.ILanguageDetector;
import LanguageDetection.domain.DomainService.LanguageAnalyzer;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Callable;

@Async
@Slf4j
public class LanguageDetectionService implements Callable<String> {
    @Getter
    @Setter
    private String url;



    @Override
    public String call() throws Exception {
        ILanguageDetector lang = new LanguageAnalyzer();
        String language = lang.analyze(url);
        log.info("A linguagem é: " + language);
        return language;
    }
}
