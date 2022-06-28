package LanguageDetection.Domain.DomainService;

import LanguageDetection.domain.DomainService.CategoryService;
import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.ICategoryRepository;
import LanguageDetection.domain.model.ValueObjects.CategoryName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

class CategoryServiceTest {

    @InjectMocks
    CategoryService categoryService;

    @Mock
    ICategoryRepository iCategoryRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ensureSaveCategoryCallICategoryRepositoryAndSaveCategory(){
        //Arrange
        Category category = new Category("Sports");

        when(iCategoryRepository.saveCategory(category)).thenReturn(category);

        //Act / Assert
        Assertions.assertEquals(categoryService.saveCategory(category), Optional.of(category));
    }

    @Test
    void ensureFindAllCategoriesReturnListOfCategoriesFromICategoryRepository(){
        //Arrange
        Category category1 = new Category("Sports");
        Category category2 = new Category("Arts");

        when(iCategoryRepository.findAll()).thenReturn(List.of(category1, category2));

        //Act / Assert
        Assertions.assertEquals(categoryService.findAll(), List.of(category1, category2));
    }

    @Test
    void ensureDeleteByNameReturnTrueFromICategoryRepositoryWhenDeleteIsSuccessful(){
        //Arrange
        CategoryName categoryName = new CategoryName("Sports");

        when(iCategoryRepository.deleteByName(categoryName)).thenReturn(true);

        //Act / Assert
        Assertions.assertTrue(categoryService.deleteByName(categoryName));
    }

    @Test
    void ensureDeleteByNameReturnFalseFromICategoryRepositoryWhenDeleteIsNotSuccessful(){
        //Arrange
        CategoryName categoryName = new CategoryName("Sports");

        when(iCategoryRepository.deleteByName(categoryName)).thenReturn(false);

        //Act / Assert
        Assertions.assertFalse(categoryService.deleteByName(categoryName));
    }

    @Test
    void findByCategoryNameShouldReturnCategoryWithMatchingName(){
        //Arrange
        CategoryName categoryName = new CategoryName("Arts");
        Category category = new Category("Arts");

        when(iCategoryRepository.findCategoryByCategoryName(categoryName)).thenReturn(Optional.of(category));

        //Act / Assert
        Assertions.assertEquals(categoryService.findCategoryByName(categoryName), Optional.of(category));
    }

}