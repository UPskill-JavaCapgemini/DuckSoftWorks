package LanguageDetection.Domain.ValueObjects;

import LanguageDetection.domain.ValueObjects.BlackListUrl;
import LanguageDetection.domain.entities.Category;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class BlackListUrlTest {

    @Test
    void shouldCreateABlackListUrlWithCorrectAttributes() throws MalformedURLException {
        BlackListUrl url = new BlackListUrl("https://stackoverflow.com/first");

        assertEquals(url.getBlackListUrlObject().toString(), "https://stackoverflow.com/first");
    }

   /* @Test
    void shouldNotCreateABlackListUrlWithIncorrectAttributes() throws MalformedURLException {
        BlackListUrl url = new BlackListUrl("ht://stackoverflow.com/first");

        Exception exception = assertThrows(MalformedURLException.class, () -> {
            BlackListUrl url1 = new BlackListUrl("");
        });

        assertTrue(exception.getMessage().contains("unknown protocol: "));
    }*/



}