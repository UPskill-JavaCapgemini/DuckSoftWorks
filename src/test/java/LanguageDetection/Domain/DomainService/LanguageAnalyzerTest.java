package LanguageDetection.Domain.DomainService;

import LanguageDetection.domain.DomainService.LanguageAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LanguageAnalyzerTest {

    @Autowired
    LanguageAnalyzer languageAnalyzer;

    @Test
    void analyzeShouldReturnEnglish() throws IOException, ParseException {
        String language = languageAnalyzer.analyze("https://www.w3.org/TR/PNG/iso_8859-1.txt");
        Assertions.assertEquals(language, "ENGLISH");
    }
}