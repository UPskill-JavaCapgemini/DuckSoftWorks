package LanguageDetection.Domain.ValueObjects;

import LanguageDetection.domain.ValueObjects.InputUrl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;


class InputUrlTest {

    String emptySpaceInputUrl = "";
    String allSymbolsInputUrl = "!%$/(()))";
    String allNumberInputUrl;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void shouldCreateInputUrlWithValidUrl() throws MalformedURLException {
        //Arrange
        String validInputUrl = "http://www.textexample.com/text/text.txt";

        //Act
        InputUrl inputUrl = new InputUrl(validInputUrl);

        //Assert
        Assert.assertNotNull(inputUrl);
    }
}