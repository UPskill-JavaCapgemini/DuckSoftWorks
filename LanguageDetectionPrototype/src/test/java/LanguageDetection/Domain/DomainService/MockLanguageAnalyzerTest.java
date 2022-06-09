package LanguageDetection.Domain.DomainService;

import LanguageDetection.domain.DomainService.MockLanguageAnalyzer;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.IOException;


class MockLanguageAnalyzerTest {


    @InjectMocks
    MockLanguageAnalyzer languageAnalyzer;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    protected void shouldCleanUpAStringWithSpecialCharacters() {
        //Arrange
        String testString = "<%a# test>< ! ,string @ with no.. special? characters.";
        String cleanedUpString;
        //Act
        cleanedUpString = languageAnalyzer.cleanUpInputText(testString);
        //Assert
        Assert.assertEquals(" a test string with no special characters ", cleanedUpString);
    }

    @org.junit.Test
    public void shouldCleanUpAStringWithMultipleSpacing() {
        //Arrange
        String testString = " a         test         string   with    multiple    spaces       ";
        String cleanedUpString;
        //Act
        cleanedUpString = languageAnalyzer.cleanUpInputText(testString);
        //Assert
        Assert.assertEquals("a test string with multiple spaces", cleanedUpString);
    }

    @Test
    public void shouldCleanUpAStringWithUpperCase() throws IOException {
        //Arrange
        String testString = "A tEsT STRing WitH UpPEr CASE";
        String lowerCaseString;
        //Act
        lowerCaseString = languageAnalyzer.cleanUpInputText(testString);
        //Assert
        Assert.assertEquals("a test string with upper case", lowerCaseString);
    }


    @Test
    public void verifyIfTheExtraSpacesAreRetrievedWithCleanUpInputText() throws IOException {
        ;
        String testString = "Bom  dia";
        String result = languageAnalyzer.cleanUpInputText(testString);
        org.testng.Assert.assertEquals(result, "bom dia");
    }

    @Test
    public void verifyIfEmptySpacesAreRetrievedWithCleanUpInputText() throws IOException {
        String testString = " ";
        String result = languageAnalyzer.cleanUpInputText(testString);
        org.testng.Assert.assertEquals(result, "");
    }
}
