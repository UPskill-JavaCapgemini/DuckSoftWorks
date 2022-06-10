//package LanguageDetection.application.services;
//import LanguageDetection.domain.entities.Category;
//import LanguageDetection.infrastructure.repositories.CategoryRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//
//@SpringBootTest
//class CategoryServiceTest {
//
//    @MockBean
//    CategoryRepository categoryRepository;
//
//    @MockBean
//    Category category;
//
//    @InjectMocks
//    CategoryService categoryService;
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        MockitoAnnotations.openMocks(this);
//    }
//
//
//    shouldCreateAndPersistACountryWithCorrectAttributes()
//
//
//
//    @Test
//    void shouldCreateACountryWithCorrectAttributes() {
//
//        // Arrange
//        when(country.getCode()).thenReturn("PT");
//        when(country.getName()).thenReturn("Portugal");
//
//        when( countryFactory.createCountry("PT", "Portugal")).thenReturn(country);
//
//        when(countryRepository.save( country )).thenReturn(country);
//
//        // Act
//        Country country = countryService.createAndSaveCountry("PT", "Portugal");
//
//        String code = country.getCode();
//        String name = country.getName();
//
//        // Assert
//        assertEquals(code, "PT");
//        assertEquals(name, "Portugal");
//    }
//
//
//
//
//   /* @Test
//    void findAll() {
//    }
//
//    @Test
//    void deleteCategory() {
//    }
//
//    @Test
//    void findById() {
//    }*/
//}