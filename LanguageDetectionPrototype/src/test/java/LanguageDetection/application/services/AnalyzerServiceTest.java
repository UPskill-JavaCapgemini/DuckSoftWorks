package LanguageDetection.application.services;

import LanguageDetection.application.services.AnalyzerService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AnalyzerServiceTest {

    @Test
   void analyzeWithEnglishTest() throws ParseException, IOException {

        AnalyzerService service = new AnalyzerService();
        String phrase = "There was once upon a time a little girl that is annoyed by having to do a lot of unit tests. That little girl is not me.";
        String result = service.analyze(phrase);
        Assert.assertEquals(result, "ENGLISH");
        }

    @Test
    void analyzWithPortugueseTest() throws ParseException, IOException {

        AnalyzerService service = new AnalyzerService();
        String phrase = "Era uma vez uma menina que ficava muito irritada quando tinha de fazer muitos testes. Essa menina não sou eu.";
        String result = service.analyze(phrase);
        Assert.assertEquals(result, "PORTUGUESE");
    }

    @Test
    void analyzeWithSpanishTest() throws ParseException, IOException {

        AnalyzerService service = new AnalyzerService();
        String phrase = "Érase una vez una niña que se enfadaba mucho cuando tenía que hacer muchos exámenes. Esa chica no soy yo.";
        String result = service.analyze(phrase);
        Assert.assertEquals(result, "SPANISH");
    }

    @Test
    void analyzeWithEnglishAndSpanishTest() throws ParseException, IOException {

        AnalyzerService service = new AnalyzerService();
        String phrase = "There was once upon a time a little girl that is annoyed by having to do a lot of unit tests. Esa chica no soy yo.";
        String result = service.analyze(phrase);
        Assert.assertEquals(result, "ENGLISH");
    }

    @Test
    void analyzeWithNotPortugueseTest() throws ParseException, IOException {

        AnalyzerService service = new AnalyzerService();
        String phrase = "There was once upon a time a little girl that is annoyed by having to do a lot of unit tests. Esa chica no soy yo.";
        String result = service.analyze(phrase);
        Assert.assertNotEquals(result, "PRTUGUESE");
    }

}