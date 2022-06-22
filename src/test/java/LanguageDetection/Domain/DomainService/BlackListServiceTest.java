package LanguageDetection.Domain.DomainService;

import LanguageDetection.domain.DomainService.BlackListService;
import LanguageDetection.domain.model.BlackListItem;
import LanguageDetection.domain.model.IBlackListItemRepository;
import LanguageDetection.domain.model.ValueObjects.InputUrl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.net.MalformedURLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BlackListServiceTest {

    @InjectMocks
    BlackListService blackListService;

    @Mock
    IBlackListItemRepository blackListItemRepository;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ensureItsPossibleToSaveABlackListItem() throws MalformedURLException {
        //Arrange
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        BlackListItem blackListItem = new BlackListItem(url);

        when(blackListItemRepository.findAllBlackListItems()).thenReturn(List.of());
        when(blackListItemRepository.saveBlackListItem(blackListItem)).thenReturn(blackListItem);

        //Act
        BlackListItem savedBlackListItem = blackListService.saveBlackListItem(blackListItem);

        //Assert
        assertEquals(savedBlackListItem, blackListItem);
    }

    @Test
    void ensureItsNotPossibleToSaveABlackListItemWhenThereIsAlreadyOneThatBlockThatOne() throws MalformedURLException {
        //Arrange
        String firstUrl = "https://www.w3.org/TR/PNG/";
        String secondUrl = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        BlackListItem firstBlackListItem = new BlackListItem(firstUrl);
        BlackListItem secondBlackListItem = new BlackListItem(secondUrl);

        when(blackListItemRepository.findAllBlackListItems()).thenReturn(List.of(firstBlackListItem));

        //Act


        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            blackListService.saveBlackListItem(secondBlackListItem);
        });

        //Assert
        Assertions.assertEquals("BlackList already blocks this URL", thrown.getMessage());
    }

    @Test
    void ensureIsBlackListedMethodReturnsTrueWhenUrlIsListedOnDatabase() throws MalformedURLException {
        //Arrange
        String inURL = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        String blackListedURL = "https://www.w3.org/";
        BlackListItem blackListItem = new BlackListItem(blackListedURL);
        InputUrl inputUrl = new InputUrl(inURL);

        //Act
        when(blackListService.findAllBlackListItems()).thenReturn(List.of(blackListItem));

        //Assert
        assertTrue(blackListService.isBlackListed(inputUrl));
    }

    @Test
    void ensureIsBlackListedMethodReturnsFalseWhenUrlIsNotListedOnDatabase() throws MalformedURLException {
        //Arrange
        String inURL = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        InputUrl inputUrl = new InputUrl(inURL);

        //Act
        when(blackListService.findAllBlackListItems()).thenReturn(List.of());

        //Assert
        assertFalse(blackListService.isBlackListed(inputUrl));
    }
}