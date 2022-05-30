package LanguageDetection.application.services;

import LanguageDetection.application.services.AnalyzerService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AnalyzerServiceTest {

    @Test
   void analyze() throws ParseException, IOException {

        AnalyzerService service = new AnalyzerService();
        String phrase = "there was once upon a time a little girl that is annoyed by having to do a lot of unit tests.";
        String result = service.analyze(phrase);
        Assert.assertEquals(result, "ENGLISH");
        }


}