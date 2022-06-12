package LanguageDetection.application.services;

import LanguageDetection.domain.DomainService.LanguageAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AnalyzerServiceTest {

    @Test
   void analyzeWithEnglishTest() throws ParseException, IOException {

        LanguageAnalyzer service = new LanguageAnalyzer();
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        String result = service.analyze(url);
        Assert.assertEquals(result, "ENGLISH");
        }

    @Test
    void analyzeWithPortugueseTest() throws ParseException, IOException {

        LanguageAnalyzer service = new LanguageAnalyzer();
        String url = "http://users.isr.ist.utl.pt/~cfb/VdS/v302.txt";
        String result = service.analyze(url);
        Assert.assertEquals(result, "PORTUGUESE");
    }

    @Test
    void analyzeWithSpanishTest() throws ParseException, IOException {

        LanguageAnalyzer service = new LanguageAnalyzer();
        String phrase = "https://firstneighbor.com/transcribed-audio/Mejor-Llamada-de-Caso-en-Espanol.txt";
        String result = service.analyze(phrase);
        Assert.assertEquals(result, "SPANISH");
    }

}
