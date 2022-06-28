package LanguageDetection.application.services;

import LanguageDetection.application.DTO.CategoryDTO;
import LanguageDetection.application.DTO.DTOAssemblers.CategoryDomainDTOAssembler;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.domain.DomainService.CategoryService;
import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.ValueObjects.CategoryName;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

class CategoryManagementServiceTest {

    @InjectMocks
    CategoryManagementService categoryManagementService;

    @Mock
    CategoryService categoryService;

    @Mock
    CategoryDomainDTOAssembler dtoAssembler;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAndSaveACategory() {
        //Arrange
        String categoryName = "Sports Science";
        NewCategoryInfoDTO categoryInfoDTO = new NewCategoryInfoDTO(categoryName);

        Category category = new Category("Sports Science");
        CategoryDTO categoryDTO = new CategoryDTO(category);
        Mockito.when(categoryService.saveCategory(any(Category.class))).thenReturn(Optional.of(category));
        Mockito.when(dtoAssembler.toDTO(any(Category.class))).thenReturn(categoryDTO);

        //Act
        Optional<CategoryDTO> opCategoryDTO = categoryManagementService.createAndSaveCategory(categoryInfoDTO);

        //Assert
        Assertions.assertEquals(opCategoryDTO, Optional.of(categoryDTO));
    }

    @Test
    void shouldReturnAllCategoriesWith2Created() {
        Category item1 = new Category("Sports Science");
        Category item2 = new Category("Music");


        Mockito.when(categoryService.findAll()).thenReturn(List.of(item1,item2));
        Mockito.when(dtoAssembler.toDTO(item1)).thenReturn(new CategoryDTO(item1));
        Mockito.when(dtoAssembler.toDTO(item2)).thenReturn(new CategoryDTO(item2));

        List<CategoryDTO> result = categoryManagementService.getAllCategory();

        Assert.assertEquals(result.size(),2);
    }

    @Test
    void deleteCategoryShouldReturnTrueWhenSuccessfullyDelete() {
        //Arrange
        NewCategoryInfoDTO categoryInfoDTO = new NewCategoryInfoDTO("Sports Science");

        Mockito.when(categoryService.deleteByName(any(CategoryName.class))).thenReturn(true);

        //Act / Assert
        Assertions.assertTrue(categoryManagementService.deleteCategory(categoryInfoDTO));
    }

    @Test
    void deleteCategoryShouldReturnFalseWhenDeleteWasNotDone() {
        //Arrange
        NewCategoryInfoDTO categoryInfoDTO = new NewCategoryInfoDTO("Sports Science");

        Mockito.when(categoryService.deleteByName(any(CategoryName.class))).thenReturn(false);

        //Act / Assert
        Assertions.assertFalse(categoryManagementService.deleteCategory(categoryInfoDTO));
    }
}
