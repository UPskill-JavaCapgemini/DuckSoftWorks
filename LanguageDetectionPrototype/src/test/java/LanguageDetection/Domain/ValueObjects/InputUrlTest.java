package LanguageDetection.Domain.ValueObjects;

import LanguageDetection.domain.ValueObjects.InputUrl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertThrows;


class InputUrlTest {


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

    @Test
    public void shouldThrowExceptionWithSpacesOnlyUrl() {
        //Arrange
        String spacesOnlyInputUrl = "             ";

        //Act
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new InputUrl(spacesOnlyInputUrl);
        });

        //Assert
        Assert.assertNotNull(illegalArgumentException.getMessage(), "The URL doesn't contain a txt file");
    }

    @Test
    public void shouldThrowExceptionWithSymbolOnlyUrl() {
        //Arrange
        String allSymbolsInputUrl = "!%$/(()))";

        //Act
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new InputUrl(allSymbolsInputUrl);
        });

        //Assert
        Assert.assertNotNull(illegalArgumentException.getMessage(), "The URL doesn't contain a txt file");
    }

    @Test
    public void shouldThrowExceptionWithNumberOnlyUrl() {
        //Arrange
        String allNumberInputUrl = "12315123151";

        //Act
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new InputUrl(allNumberInputUrl);
        });

        //Assert
        Assert.assertNotNull(illegalArgumentException.getMessage(), "The URL doesn't contain a txt file");
    }
}

