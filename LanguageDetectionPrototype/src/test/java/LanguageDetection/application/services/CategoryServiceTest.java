package LanguageDetection.application.services;
import LanguageDetection.application.DTO.CategoryDTO;
import LanguageDetection.application.DTO.DTOAssemblers.CategoryDomainDTOAssembler;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.ICategory;
import LanguageDetection.infrastructure.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



class CategoryServiceTest {
//
//    @Mock
//    CategoryDomainDTOAssembler dtoAssembler;
//
//    @Mock
//    ICategory iCategory;
//
//    @InjectMocks
//    CategoryService categoryService;
//
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//
//    @Test
//    void shouldCreateACategoryWithCorrectAttributes() {
//
//        NewCategoryInfoDTO specificDTO = new NewCategoryInfoDTO("Economics");
//        Category category = new Category("Economics");
//        CategoryDTO categoryDTO = new CategoryDTO(category);
//
//        // Arrange
//        when(iCategory.saveCategory(category)).thenReturn(category);
//        when(dtoAssembler.toDTO(category)).thenReturn(categoryDTO);
//
//        // Act
//        Optional<CategoryDTO> categoryReturn = categoryService.createAndSaveCategory(specificDTO);
//
//        String catName = categoryReturn.toString();
//
//        // Assert
//        assertEquals(catName, "Economics");
//    }
//
//


   /* @Test
    void findAll() {
    }

    @Test
    void deleteCategory() {
    }

    @Test
    void findById() {
    }*/
}