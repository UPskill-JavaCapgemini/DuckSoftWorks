/*
package LanguageDetection.Domain.model.ValueObjects;

import LanguageDetection.domain.model.ValueObjects.Text;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class TextTest {

    @InjectMocks
    Text text;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    protected void shouldCleanUpAStringWithSpecialCharacters() {
        //Arrange
        String testString = "<%a# test>< ! ,string @ with no.. special? characters.";
        String cleanedUpString;
        //Act
        cleanedUpString = text.cleanUpInputText(testString);
        //Assert
        Assert.assertEquals(" a test string with no special characters ", cleanedUpString);
    }

    @org.junit.Test
    public void shouldCleanUpAStringWithMultipleSpacing() {
        //Arrange
        String testString = " a         test         string   with    multiple    spaces       ";
        String cleanedUpString;
        //Act
        cleanedUpString = text.cleanUpInputText(testString);
        //Assert
        Assert.assertEquals("a test string with multiple spaces", cleanedUpString);
    }

    @Test
    public void shouldCleanUpAStringWithUpperCase() {
        //Arrange
        String testString = "A tEsT STRing WitH UpPEr CASE";
        String lowerCaseString;
        //Act
        lowerCaseString = text.cleanUpInputText(testString);
        //Assert
        Assert.assertEquals("a test string with upper case", lowerCaseString);
    }


    @Test
    public void verifyIfTheExtraSpacesAreRetrievedWithCleanUpInputText() {
        ;
        String testString = "Bom  dia";
        String result = text.cleanUpInputText(testString);
        org.testng.Assert.assertEquals(result, "bom dia");
    }

    @Test
    public void verifyIfEmptySpacesAreRetrievedWithCleanUpInputText() {
        String testString = " ";
        String result = text.cleanUpInputText(testString);
        org.testng.Assert.assertEquals(result, "");
    }


@Test
    void getTextContent() {
    }

}
*/
