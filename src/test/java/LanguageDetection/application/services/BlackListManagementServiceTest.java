package LanguageDetection.application.services;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.application.DTO.DTOAssemblers.BlackListDomainDTOAssembler;
import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.domain.entities.BlackListItem;
import LanguageDetection.domain.entities.IBlackListRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

class BlackListManagementServiceTest {

    @Mock
    private IBlackListRepository iBlackListRepository;
    @Mock
    BlackListDomainDTOAssembler assembler;
    @InjectMocks
    private BlackListManagementService blackListManagementService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    /*@Test
    void shouldCreateAndSaveABlackListItemWithValidURL() throws MalformedURLException {
        String Url = "https://stackoverflow.com/first";
        BlackListItem item1 = new BlackListItem(Url);
        Mockito.when(iBlackListRepository.isBlackListed(item1)).thenReturn(false);
        Mockito.when(iBlackListRepository.saveBlackListItem(item1)).thenReturn(item1);
        Mockito.when(assembler.toDTO(Mockito.any())).thenCallRealMethod();
        when(iBlackListRepository.findByBlackListItem(item1)).thenReturn(Optional.empty());

        Optional<BlackListDTO> optional =  blackListManagementService.createAndSaveBlackListItem(new NewBlackListInfoDTO(Url));
        Assert.assertTrue(optional.isPresent());
        Assert.assertTrue(optional.get().getUrl().toString().equals(Url));
    }*/

    @Test
    void shouldSuccessefullyDeleteABlackListItem() throws MalformedURLException {
        String Url = "https://stackoverflow.com/first";
        BlackListItem blackListItem1 = new BlackListItem(Url);
        NewBlackListInfoDTO newBlackListInfoDTOitem1 = new NewBlackListInfoDTO(Url);

        Mockito.when(iBlackListRepository.deleteByBlackListUrl(blackListItem1)).thenReturn(true);

        Assert.assertTrue(blackListManagementService.deleteBlackListItem(newBlackListInfoDTOitem1));
    }


    @Test
    void shouldReturnAllBlackListItemsWith2Created() throws MalformedURLException {
        BlackListItem item1 = new BlackListItem("https://stackoverflow.com/first");
        BlackListItem item2 = new BlackListItem("https://stackoverflow.com/second");

        Mockito.when(iBlackListRepository.findAllBlackListItems()).thenReturn(List.of(item1,item2));
        Mockito.when(assembler.toDTO(item1)).thenReturn(new BlackListDTO(item1));
        Mockito.when(assembler.toDTO(item2)).thenReturn(new BlackListDTO(item2));

        List<BlackListDTO> result = blackListManagementService.getAllBlackListItems();

        Assert.assertEquals(result.size(),2);
    }

    @Test
    void shouldReturnAllBlackListItemsWith1createdAssertingFalse() throws MalformedURLException {
        BlackListItem item1 = new BlackListItem("https://stackoverflow.com/first");

        Mockito.when(iBlackListRepository.findAllBlackListItems()).thenReturn(List.of(item1));
        Mockito.when(assembler.toDTO(item1)).thenReturn(new BlackListDTO(item1));

        List<BlackListDTO> result = blackListManagementService.getAllBlackListItems();

        Assert.assertNotEquals(result.size(),2);
    }

    @Test
    void shouldAssertTrueIfItemIsAlreadyInTheBlackList() throws MalformedURLException {
        NewBlackListInfoDTO item1 = new NewBlackListInfoDTO("https://stackoverflow.com/first");

        Mockito.when(iBlackListRepository.isBlackListed(Mockito.any())).thenReturn(true);

        Assert.assertTrue(blackListManagementService.isBlackListed(item1));
    }


}
