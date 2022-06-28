package LanguageDetection.Domain.model.ValueObjects;

import LanguageDetection.domain.model.ValueObjects.CategoryName;
import LanguageDetection.domain.model.ValueObjects.Text;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TextTest {

//TODO: Its needed to do tests to confirm verifyTextSize method

    @Test
    protected void ensureTextCleanUpAStringWithSpecialCharacters() {
        //Arrange
        String testString = "<%a# test>< ! ,string @ with no.. special? characters.";
        String cleanedUpString = " a test string with no special characters ";
        //Act
        Text text = new Text(testString);
        //Assert
        Assertions.assertEquals(text.getTextContent(), cleanedUpString);
    }

    @org.junit.Test
    public void ensureTextCleanUpAStringWithMultipleSpacing() {
        //Arrange
        String testString = " a         test         string   with    multiple    spaces       ";
        String cleanedUpString = "a test string with multiple spaces";
        //Act
        Text text = new Text(testString);
        //Assert
        Assertions.assertEquals(text.getTextContent(), cleanedUpString);
    }

    @Test
    public void ensureTextCleanUpAStringWithUpperCase() {
        //Arrange
        String testString = "A tEsT STRing WitH UpPEr CASE";
        String lowerCaseString = "a test string with upper case";
        //Act
        Text text = new Text(testString);
        //Assert
        Assertions.assertEquals(text.getTextContent(), lowerCaseString);
    }


    @Test
    public void verifyIfTheExtraSpacesAreRetrievedWithCleanUpInputText() {
        ;
        String testString = "Bom  dia";
        String result = "bom dia";
        Text text = new Text(testString);
        Assertions.assertEquals(text.getTextContent(), result);
    }

    @Test
    public void ensureTextIsNotCreatedWhenIsOnlySpaces(){
        String testString = "                                      ";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Text(testString);
        });

        Assert.assertEquals(illegalArgumentException.getMessage(), "The text is not valid");
    }

    @Test
    public void ensureTextIsNotCreatedWhenIsEmptyString(){
        String testString = "";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Text(testString);
        });

        Assert.assertEquals(illegalArgumentException.getMessage(), "The text is not valid");
    }

    @Test
    public void ensureTextIsNotCreatedWhenTextIsChineseCharacters(){
        String testString = "漢語漢語漢語漢語漢語漢語";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Text(testString);
        });

        Assert.assertEquals(illegalArgumentException.getMessage(), "The text is not valid");
    }

    @Test
    public void ensureTextIsNotCreatedWhenTextIsJapaneseCharacters(){
        String testString = "平仮名平仮名平仮名平仮名平仮名";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Text(testString);
        });

        Assert.assertEquals(illegalArgumentException.getMessage(), "The text is not valid");
    }

    @Test
    public void ensureTextIsNotCreatedWhenTextIsCyrillicCharacters(){
        String testString = "а́збукаа́збукаа́збука";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Text(testString);
        });

        Assert.assertEquals(illegalArgumentException.getMessage(), "The text is not valid");
    }

    @Test
    public void ensureTextIsNotCreatedWhenTextIsArabicCharacters(){
        String testString = "العربيةالعربية";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new Text(testString);
        });

        Assert.assertEquals(illegalArgumentException.getMessage(), "The text is not valid");
    }

}
