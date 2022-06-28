package LanguageDetection.Domain.model;

import LanguageDetection.domain.model.BlackListItem;
import LanguageDetection.domain.model.ValueObjects.TimeOut;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;


class BlackListItemTest {

    @Test
    public void ensureBlackListItemIsCreatedWithCorrectURL() throws MalformedURLException {
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        BlackListItem blackListItem = new BlackListItem(url);

        Assert.notNull(blackListItem);
    }

    @Test
    public void ensureBlackListItemIsNotCreatedWithIncorrectURL(){
        String url = "www.w3.org/TR/PNG/iso_8859-1.txt";


        MalformedURLException malformedURLException = assertThrows(MalformedURLException.class, () -> {
            BlackListItem blackListItem= new BlackListItem(url);
        });
        //Assert
        Assertions.assertEquals(malformedURLException.getMessage(), "no protocol: www.w3.org/TR/PNG/iso_8859-1.txt");
    }

    @Test
    public void ensureBlackListItemIsNotEqualWhenThereAreTwoDifferentObjects() throws MalformedURLException {
        String url1 = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        BlackListItem blackListItem1 = new BlackListItem(url1);

        String url2 = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        BlackListItem blackListItem2 = new BlackListItem(url2);

        assertFalse(blackListItem1.sameAs(blackListItem2));
    }


}