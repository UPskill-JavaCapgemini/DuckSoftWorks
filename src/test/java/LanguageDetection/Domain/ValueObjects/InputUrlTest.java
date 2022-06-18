package LanguageDetection.Domain.ValueObjects;

import LanguageDetection.domain.model.ValueObjects.InputUrl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertThrows;


class InputUrlTest {


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
    public void shouldCreateInputUrlWithValidLocalUrl() throws MalformedURLException {
        //Arrange
        String validLocalInputUrl = "file:///C:/Users/danil/Documents/UPskill/LABP/example.txt";

        //Act
        InputUrl inputUrl = new InputUrl(validLocalInputUrl);

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
        Assert.assertEquals(illegalArgumentException.getMessage(), "The URL doesn't contain a txt file or it´s not valid");
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
        Assert.assertEquals(illegalArgumentException.getMessage(), "The URL doesn't contain a txt file or it´s not valid");
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
        Assert.assertEquals(illegalArgumentException.getMessage(), "The URL doesn't contain a txt file or it´s not valid");
    }

    @Test
    public void shouldThrowExceptionWithNoProtocolUrl(){
        //Arrange
        String noProtocolInputUrl = "www.noprotocol.com.txt";

        //Act
        MalformedURLException malformedURLException = assertThrows(MalformedURLException.class, () -> {
            new InputUrl(noProtocolInputUrl);
        });

        //Assert
        Assert.assertEquals(malformedURLException.getMessage(), "no protocol: www.noprotocol.com.txt");
    }
}

