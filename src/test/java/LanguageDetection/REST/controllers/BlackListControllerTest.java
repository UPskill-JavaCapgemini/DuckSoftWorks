package LanguageDetection.REST.controllers;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.application.services.BlackListManagementService;
import LanguageDetection.domain.model.BlackListItem;
import LanguageDetection.REST.controllers.BlackListController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BlackListControllerTest {

    @InjectMocks
    BlackListController blackListController;

    @Mock
    BlackListManagementService blackListManagementService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBlackListItemsShouldReturnHttpStatus200() throws MalformedURLException {
        //Arrange
        BlackListItem blackListItem = new BlackListItem("https://www.w3.org/TR/PNG/iso_8859-1.txt");
        BlackListDTO blackListDTO = new BlackListDTO(blackListItem);

        when(blackListManagementService.getAllBlackListItems()).thenReturn(List.of(blackListDTO));

        //Act
        ResponseEntity<List<BlackListDTO>> responseEntity = blackListController.getAllBlackListItems();

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @Test
    void ensureCreateAndSaveBlackListItemReturnHttpStatus201WhenBlackListItemIsCreated() throws MalformedURLException {
        //Arrange
        NewBlackListInfoDTO blackListInfoDTO = new NewBlackListInfoDTO("https://www.w3.org/TR/PNG/iso_8859-1.txt");

        BlackListItem blackListItem = new BlackListItem("https://www.w3.org/TR/PNG/iso_8859-1.txt");
        BlackListDTO blackListDTO = new BlackListDTO(blackListItem);

        when(blackListManagementService.createAndSaveBlackListItem(blackListInfoDTO)).thenReturn(Optional.of(blackListDTO));

        //Act
        ResponseEntity<Object> responseEntity = blackListController.createAndSaveBlackListItem(blackListInfoDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);
    }

    @Test
    void ensureCreateAndSaveBlackListItemReturnHttpStatus400WhenBlackListItemIsCreated() {
        //Arrange
        NewBlackListInfoDTO blackListInfoDTO = new NewBlackListInfoDTO("https://www.w3.org/TR/PNG/iso_8859-1.txt");

        when(blackListManagementService.createAndSaveBlackListItem(blackListInfoDTO)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<Object> responseEntity = blackListController.createAndSaveBlackListItem(blackListInfoDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 400);
    }

    @Test
    void ensureDeleteBlackListItemReturnHttpStatus200WhenDeleteIsSuccessful(){
        //Arrange
        NewBlackListInfoDTO newBlackListInfoDTO = new NewBlackListInfoDTO("https://www.w3.org/TR/PNG/iso_8859-1.txt");

        when(blackListManagementService.deleteBlackListItem(newBlackListInfoDTO)).thenReturn(true);

        //Act
        ResponseEntity<Object> responseEntity = blackListController.deleteBlackListItem(newBlackListInfoDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

    }

    @Test
    void ensureDeleteBlackListItemReturnHttpStatus404WhenUnableToDelete(){
        //Arrange
        NewBlackListInfoDTO newBlackListInfoDTO = new NewBlackListInfoDTO("https://www.w3.org/TR/PNG/iso_8859-1.txt");

        when(blackListManagementService.deleteBlackListItem(newBlackListInfoDTO)).thenReturn(false);

        //Act
        ResponseEntity<Object> responseEntity = blackListController.deleteBlackListItem(newBlackListInfoDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 404);

    }
}
