package LanguageDetection.application.services;

import LanguageDetection.application.DTO.CategoryDTO;
import LanguageDetection.application.DTO.DTOAssemblers.CategoryDomainDTOAssembler;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.ICategoryRepository;
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

import static org.mockito.ArgumentMatchers.any;


class CategoryServiceTest {

    @Mock
    private ICategoryRepository iCategoryRepository;

    @Mock
    CategoryDomainDTOAssembler assembler;


    @InjectMocks
    private CategoryService categoryService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldCreateAndSaveCategory(){
        String catName = "History";
        Category item1 = new Category(catName);

        Mockito.when(iCategoryRepository.findCategoryById(item1)).thenReturn(any());
        Mockito.when(iCategoryRepository.saveCategory(item1)).thenReturn(item1);
        Mockito.when(assembler.toDTO(item1)).thenReturn(new CategoryDTO(item1));

        Optional<CategoryDTO> optional =  categoryService.createAndSaveCategory(new NewCategoryInfoDTO(catName));
        Assert.assertTrue(!optional.isEmpty());
        Assert.assertTrue(optional.get().getCategory().toString().equals(catName));
    }

    @Test
    void shouldSuccessefullyDeleteACategory() {
        String catName = "History";
        Category item1 = new Category(catName);
        NewCategoryInfoDTO newCategoryInfoDTOitem1 = new NewCategoryInfoDTO(catName);



        Mockito.when(iCategoryRepository.deleteByName(any(Category.class))).thenReturn(true);

        Assert.assertTrue(categoryService.deleteCategory(newCategoryInfoDTOitem1));
    }

    @Test
    void getAllCategoryItemsWith2CreatedAssertingTrue() throws MalformedURLException {
        Category item1 = new Category("Arts");
        Category item2 = new Category("Science");

        Mockito.when(iCategoryRepository.findAll()).thenReturn(List.of(item1,item2));
        Mockito.when(assembler.toDTO(item1)).thenReturn(new CategoryDTO(item1));
        Mockito.when(assembler.toDTO(item2)).thenReturn(new CategoryDTO(item2));

        List<CategoryDTO> result = categoryService.getAllCategory();

        Assert.assertEquals(result.size(),2);
    }

    @Test
    void getAllCategoryItemsWith1CreatedAssertingFalse() throws MalformedURLException {
        Category item1 = new Category("Arts");

        Mockito.when(iCategoryRepository.findAll()).thenReturn(List.of(item1));
        Mockito.when(assembler.toDTO(item1)).thenReturn(new CategoryDTO(item1));

        List<CategoryDTO> result = categoryService.getAllCategory();

        Assert.assertNotEquals(result.size(),2);
    }

    @Test
    void categoryExistsfindByCategoryId(){
        Category item1 = new Category("History");
        Optional<Category> op = Optional.of(item1);

        Mockito.when(iCategoryRepository.findCategoryById(any())).thenReturn(op);

        Assert.assertSame(categoryService.findCategoryByName(item1), op);

    }

}