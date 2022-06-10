package LanguageDetection.Domain.ValueObjects;

import LanguageDetection.domain.ValueObjects.BlackListUrl;
import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.entities.Category;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class BlackListUrlTest {

    @Test
    void shouldCreateABlackListUrlWithCorrectAttributes() throws MalformedURLException {
        BlackListUrl url = new BlackListUrl("https://stackoverflow.com/first");

        assertEquals(url.getBlackListUrlObject().toString(), "https://stackoverflow.com/first");
    }

    @Test
    void shouldNotCreateABlackListUrlWithIncorrectAttributes()  {
        String noProtocolBlackListUrl = "ht//noprotocol.com";

        MalformedURLException malformedURLException = assertThrows(MalformedURLException.class, () -> {
            new BlackListUrl(noProtocolBlackListUrl);
        });

        Assert.assertEquals(malformedURLException.getMessage(), "no protocol: ht//noprotocol.com");
    }

    @Test
    void shouldThrowExceptionAndNotCreateABlackListUrlWithBlankUrl()  {
        String noProtocolBlackListUrl = "";

        MalformedURLException malformedURLException = assertThrows(MalformedURLException.class, () -> {
            new BlackListUrl(noProtocolBlackListUrl);
        });

        Assert.assertEquals(malformedURLException.getMessage(), "no protocol: ");
    }


    @Test
    public void shouldThrowExceptionAndNotCreateAABlackListUrlWithSpacesOnlyUrl() {
        //Arrange
        String spacesOnlyBlackListUrl = "             ";

        //Act
        MalformedURLException malformedURLException = assertThrows(MalformedURLException.class, () -> {
            new BlackListUrl(spacesOnlyBlackListUrl);
        });

        //Assert
        Assert.assertEquals(malformedURLException.getMessage(), "no protocol:              ");
    }

}