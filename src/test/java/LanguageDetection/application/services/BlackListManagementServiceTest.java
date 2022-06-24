package LanguageDetection.application.services;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.application.DTO.DTOAssemblers.BlackListDomainDTOAssembler;
import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.domain.DomainService.BlackListService;
import LanguageDetection.domain.model.BlackListItem;
import LanguageDetection.domain.model.ValueObjects.BlackListUrl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


class BlackListManagementServiceTest {

    @Mock
    BlackListDomainDTOAssembler assembler;
    @InjectMocks
    BlackListManagementService blackListManagementService;
    @Mock
    BlackListService blackListService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAndSaveABlackListItemWithValidURL() throws MalformedURLException {
        //Arrange
        String url = "https://stackoverflow.com/first";
        NewBlackListInfoDTO blackListInfoDTO = new NewBlackListInfoDTO(url);

        BlackListItem blackListItem = new BlackListItem("https://stackoverflow.com/first");
        BlackListDTO blackListDTO = new BlackListDTO(blackListItem);
        Mockito.when(blackListService.saveBlackListItem(any(BlackListItem.class))).thenReturn(blackListItem);
        Mockito.when(assembler.toDTO(any(BlackListItem.class))).thenReturn(blackListDTO);

        //Act
        Optional<BlackListDTO> opBlackListDTO = blackListManagementService.createAndSaveBlackListItem(blackListInfoDTO);

        //Assert
        Assertions.assertEquals(opBlackListDTO, Optional.of(blackListDTO));
    }

    @Test
    void deleteBlackListItemShouldReturnTrueWhenSuccessfullyDelete() {
        //Arrange
        String url = "https://stackoverflow.com/first";
        NewBlackListInfoDTO blackListInfoDTO = new NewBlackListInfoDTO(url);

        Mockito.when(blackListService.deleteByBlackListUrl(any(BlackListUrl.class))).thenReturn(true);

        //Act / Assert
        Assertions.assertTrue(blackListManagementService.deleteBlackListItem(blackListInfoDTO));
    }

    @Test
    void deleteBlackListItemShouldReturnFalseWhenDeleteWasNotDone() {
        //Arrange
        String url = "https://stackoverflow.com/first";
        NewBlackListInfoDTO blackListInfoDTO = new NewBlackListInfoDTO(url);

        Mockito.when(blackListService.deleteByBlackListUrl(any(BlackListUrl.class))).thenReturn(false);

        //Act / Assert
        Assertions.assertFalse(blackListManagementService.deleteBlackListItem(blackListInfoDTO));
    }


    @Test
    void shouldReturnAllBlackListItemsWith2Created() throws MalformedURLException {
        BlackListItem item1 = new BlackListItem("https://stackoverflow.com/first");
        BlackListItem item2 = new BlackListItem("https://stackoverflow.com/second");


        Mockito.when(blackListService.findAllBlackListItems()).thenReturn(List.of(item1,item2));
        Mockito.when(assembler.toDTO(item1)).thenReturn(new BlackListDTO(item1));
        Mockito.when(assembler.toDTO(item2)).thenReturn(new BlackListDTO(item2));

        List<BlackListDTO> result = blackListManagementService.getAllBlackListItems();

        Assert.assertEquals(result.size(),2);
    }
}
