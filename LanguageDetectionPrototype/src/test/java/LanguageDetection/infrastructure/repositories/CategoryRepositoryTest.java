package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.entities.Category;
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
        category1 = new Category("Sports");
        category2 = new Category("Economics");
    }


    @Test
    public void findAllCategoryShouldReturn4() {
        //given
        Category category3 = new Category("Arts");
        Category category4 = new Category("Economy");

        //when
        when(jpaRepository.findAll()).thenReturn(List.of(category1, category2, category3, category4));

        Assertions.assertEquals(categoryRepository.findAll(), List.of(category1, category2, category3, category4));
    }

    @Test
    public void shouldAvoidCreationOfCategoryThatIsBaseCategory() {
        Optional<Category> cat = Optional.of(category1);

        Assert.assertTrue(categoryRepository.isBaseCategory(cat));
    }

    @Test
    public void shouldNotAvoidCreationOfCategoryThatIsNotBaseCategory() {
        Category categoryBase = new Category("Ducks)");
        Optional<Category> cat = Optional.of(categoryBase);

        Assert.assertFalse(categoryRepository.isBaseCategory(cat));
    }

    @Test
    public void shouldReturnCategory() {

        when(jpaRepository.findByCategoryName(category1.getCategoryName())).thenReturn(Optional.of(category1));

        Assert.assertEquals(categoryRepository.findCategoryById(category1), Optional.of(category1));

    }
}
