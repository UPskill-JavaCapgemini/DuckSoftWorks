package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.ValueObjects.CategoryName;
import LanguageDetection.infrastructure.repositories.JPARepositories.CategoryJpaRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;


public class CategoryRepositoryTest {

    @InjectMocks
    CategoryRepository categoryRepository;

    @Mock
    CategoryJpaRepository jpaRepository;

    Category category1;
    Category category2;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void beforeEach() {
        //Arrange
        category1 = new Category("Sports");
        category2 = new Category("Economics");
    }


    @Test
    public void findAllCategoryShouldReturn4() {
        //Arrange
        Category category3 = new Category("Arts");
        Category category4 = new Category("Economy");

        when(jpaRepository.findAll()).thenReturn(List.of(category1, category2, category3, category4));

        //Assert / Act
        Assertions.assertEquals(categoryRepository.findAll(), List.of(category1, category2, category3, category4));
    }

    @Test
    public void shouldFindCategoryByCategoryNameWhenCategoryNameIsPresent() {

        CategoryName categoryName = new CategoryName("Sports");

        //Arrange
        when(jpaRepository.findByCategoryName(categoryName)).thenReturn(Optional.of(category1));

        //Assert / Act
        Assertions.assertEquals(categoryRepository.findCategoryByCategoryName(categoryName), Optional.of(category1));
    }

    @Test
    public void saveCategoryShouldCallJPARepositoryToSaveATask(){
        //Arrange
        when(jpaRepository.save(category1)).thenReturn(category1);

        //Act / Assert
        Assertions.assertEquals(categoryRepository.saveCategory(category1), category1);
    }

    @Test
    public void deleteByNameShouldReturnFalseIfCategoryIsNotDeleted(){
        //Arrange
        CategoryName categoryName = new CategoryName("Sports");
        when(jpaRepository.deleteByCategoryNameAndIsBaseCategoryFalse(categoryName)).thenReturn(0);

        //Act / Assert
        Assertions.assertFalse(categoryRepository.deleteByName(categoryName));
    }

    @Test
    public void deleteByNameShouldReturnTrueIfCategoryIsDeleted(){
        //Arrange
        CategoryName categoryName = new CategoryName("Sports");
        when(jpaRepository.deleteByCategoryNameAndIsBaseCategoryFalse(categoryName)).thenReturn(1);

        //Act / Assert
        Assertions.assertTrue(categoryRepository.deleteByName(categoryName));
    }
}
