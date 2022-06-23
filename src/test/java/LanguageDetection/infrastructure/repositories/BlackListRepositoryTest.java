package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.model.BlackListItem;
import LanguageDetection.domain.model.ValueObjects.BlackListUrl;
import LanguageDetection.infrastructure.repositories.JPARepositories.BlackListJpaRepository;
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
import static org.mockito.Mockito.when;

class BlackListRepositoryTest {

    @InjectMocks
    BlackListRepository blackListRepository;

    @Mock
    BlackListJpaRepository blackListJpaRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBlackListItemShouldReturnSameObjectWhenSaved() throws MalformedURLException {
        //Arrange
        BlackListItem blackListItem = new BlackListItem("https://www.w3.org/TR/PNG/iso_8859-1.txt");
        when(blackListJpaRepository.save(blackListItem)).thenReturn(blackListItem);

        //Act / Assert
        Assertions.assertEquals(blackListRepository.saveBlackListItem(blackListItem), blackListItem);
    }

    @Test
    void deleteBlackListItemShouldReturnFalseWhenItWasNotDeleted(){
        //TODO
    }

    @Test
    void deleteBlackListItemShouldReturnTrueWhenDeleteIsSuccessful(){
        //TODO
    }

    @Test
    void findAllBlackListItemsShouldReturnListOf1BlackListItems() throws MalformedURLException {
        //Arrange
        BlackListItem blackListItem = new BlackListItem("https://www.w3.org/TR/PNG/iso_8859-1.txt");
        when(blackListJpaRepository.findAll()).thenReturn(List.of(blackListItem));

        //Act / Assert
        Assertions.assertEquals(blackListRepository.findAllBlackListItems(), List.of(blackListItem));

    }
}