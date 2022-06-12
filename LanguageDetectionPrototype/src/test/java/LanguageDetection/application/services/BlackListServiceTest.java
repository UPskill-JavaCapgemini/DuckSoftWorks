package LanguageDetection.application.services;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.application.DTO.DTOAssemblers.BlackListDomainDTOAssembler;
import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.domain.entities.BlackListItem;
import LanguageDetection.domain.entities.IBlackListItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

class BlackListServiceTest {

    @Mock
    private IBlackListItem iBlackListItem;
    @Mock
    BlackListDomainDTOAssembler assembler;
    @InjectMocks
    private BlackListService blackListService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAndSaveABlackListItem() throws MalformedURLException {
        String Url = "https://stackoverflow.com/first";
        BlackListItem item1 = new BlackListItem(Url);
        Mockito.when(iBlackListItem.isBlackListed(item1)).thenReturn(false);
        Mockito.when(iBlackListItem.saveBlackListItem(item1)).thenReturn(item1);
        Mockito.when(assembler.toDTO(Mockito.any())).thenCallRealMethod();
        when(iBlackListItem.findByBlackListItem(item1)).thenReturn(Optional.of(item1));

        Optional<BlackListDTO> optional =  blackListService.createAndSaveBlackListItem(new NewBlackListInfoDTO(Url));
        Assert.assertTrue(!optional.isEmpty());
        Assert.assertTrue(optional.get().getUrl().toString().equals(Url));
    }

    @Test
    void shouldSuccessefullyDeleteABlackListItem() throws MalformedURLException {
        String Url = "https://stackoverflow.com/first";
        BlackListItem blackListItem1 = new BlackListItem(Url);
        NewBlackListInfoDTO newBlackListInfoDTOitem1 = new NewBlackListInfoDTO(Url);

        Mockito.when(iBlackListItem.deleteByBlackListUrl(blackListItem1)).thenReturn(true);

        Assert.assertTrue(blackListService.deleteBlackListItem(newBlackListInfoDTOitem1));
    }


    @Test
    void shouldReturnAllBlackListItemsWith2Created() throws MalformedURLException {
        BlackListItem item1 = new BlackListItem("https://stackoverflow.com/first");
        BlackListItem item2 = new BlackListItem("https://stackoverflow.com/second");

        Mockito.when(iBlackListItem.findAllBlackListItems()).thenReturn(List.of(item1,item2));
        Mockito.when(assembler.toDTO(item1)).thenReturn(new BlackListDTO(item1));
        Mockito.when(assembler.toDTO(item2)).thenReturn(new BlackListDTO(item2));

        List<BlackListDTO> result = blackListService.getAllBlackListItems();

        Assert.assertEquals(result.size(),2);
    }

    @Test
    void shouldReturnAllBlackListItemsWith1createdAssertingFalse() throws MalformedURLException {
        BlackListItem item1 = new BlackListItem("https://stackoverflow.com/first");

        Mockito.when(iBlackListItem.findAllBlackListItems()).thenReturn(List.of(item1));
        Mockito.when(assembler.toDTO(item1)).thenReturn(new BlackListDTO(item1));

        List<BlackListDTO> result = blackListService.getAllBlackListItems();

        Assert.assertNotEquals(result.size(),2);
    }

    @Test
    void shouldAssertTrueIfItemIsAlreadyInTheBlackList() throws MalformedURLException {
        NewBlackListInfoDTO item1 = new NewBlackListInfoDTO("https://stackoverflow.com/first");

        Mockito.when(iBlackListItem.isBlackListed(Mockito.any())).thenReturn(true);

        Assert.assertTrue(blackListService.isBlackListed(item1));
    }


}
