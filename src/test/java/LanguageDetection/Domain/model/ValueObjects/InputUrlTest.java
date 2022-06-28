package LanguageDetection.Domain.model.ValueObjects;

import LanguageDetection.domain.model.ValueObjects.InputUrl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertThrows;


class InputUrlTest {


    @Test
    public void shouldCreateInputUrlWithValidUrl() throws MalformedURLException {
        //Arrange
        String validInputUrl = "https://www.w3.org/TR/PNG/iso_8859-1.txt";

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

