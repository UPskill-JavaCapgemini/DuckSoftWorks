package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.entities.Category;
import LanguageDetection.infrastructure.repositories.JPARepositories.CategoryJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryRepositoryTest {

    @InjectMocks
    CategoryRepository categoryRepository;

    @Mock
    CategoryJpaRepository jpaRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void findAllCategory() {
        //given
        Category category1 = new Category("Arts");
        Category category2 = new Category("Economy");

        //when
        when(jpaRepository.findAll()).thenReturn(List.of(category1,category2));

        Assertions.assertEquals(categoryRepository.findAll(), List.of(category1,category2));
    }
}
