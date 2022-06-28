package LanguageDetection.REST.controllers;

import LanguageDetection.application.DTO.CategoryDTO;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.application.services.CategoryManagementService;
import LanguageDetection.domain.model.Category;
import LanguageDetection.REST.controllers.CategoryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryControllerTest {

    @InjectMocks
    CategoryController categoryController;

    @Mock
    CategoryManagementService categoryManagementService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategoriesShouldReturnHttpStatus200(){
        //Arrange
        Category category = new Category("Sports");
        CategoryDTO categoryDTO = new CategoryDTO(category);

        when(categoryManagementService.getAllCategory()).thenReturn(List.of(categoryDTO));

        //Act
        ResponseEntity<List<CategoryDTO>> responseEntity = categoryController.getAllCategories();

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @Test
    void ensureCreateAndSaveCategoryReturnHttpStatus201WhenCategoryIsPresent(){
        //Arrange
        Category category = new Category("Sports");
        CategoryDTO categoryDTO = new CategoryDTO(category);

        NewCategoryInfoDTO categoryInfoDTO = new NewCategoryInfoDTO("Sports");

        when(categoryManagementService.createAndSaveCategory(categoryInfoDTO)).thenReturn(Optional.of(categoryDTO));

        //Act
        ResponseEntity<Object> responseEntity = categoryController.createAndSaveCategory(categoryInfoDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);
    }

    @Test
    void ensureCreateAndSaveCategoryReturnHttpStatus400WhenCategoryIsNotPresent(){
        //Arrange
        Category category = new Category("Sports");
        CategoryDTO categoryDTO = new CategoryDTO(category);

        NewCategoryInfoDTO categoryInfoDTO = new NewCategoryInfoDTO("Sports");

        when(categoryManagementService.createAndSaveCategory(categoryInfoDTO)).thenReturn(Optional.empty());

        //Act
        ResponseEntity<Object> responseEntity = categoryController.createAndSaveCategory(categoryInfoDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 400);
    }

    @Test
    void ensureDeleteCategoryReturnHttpStatus200WhenCategoryIsSuccessfulDeleted(){
        //Arrange
        NewCategoryInfoDTO categoryInfoDTO = new NewCategoryInfoDTO("Sports Science");

        when(categoryManagementService.deleteCategory(categoryInfoDTO)).thenReturn(true);

        //Act
        ResponseEntity<Object> responseEntity = categoryController.deleteCategory(categoryInfoDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);
    }

    @Test
    void ensureDeleteCategoryReturnHttpStatus400WhenCategoryIsNotDeleted(){
        //Arrange
        NewCategoryInfoDTO categoryInfoDTO = new NewCategoryInfoDTO("Sports Science");

        when(categoryManagementService.deleteCategory(categoryInfoDTO)).thenReturn(false);

        //Act
        ResponseEntity<Object> responseEntity = categoryController.deleteCategory(categoryInfoDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 400);
    }

}
