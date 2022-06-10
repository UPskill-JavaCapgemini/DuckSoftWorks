package LanguageDetection.application.services;
import LanguageDetection.domain.entities.Category;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {


    @Test
    void shouldCreateACategoryWithCorrectAttributes() {
        Category category = new Category("Arts");

        assertEquals(category.getCategoryName().toString(), "Arts");
    }

//    @Test
//    void shouldNotCreateACategoryWithInvalidAttributes() {
//        Category category = new Category("");
//
//        assertEquals(category.getCategoryName().toString());
//    }

    @Test
    public void shouldNotCreateACategoryWithEmptyAttribute_IllegalArgumentExceptionExpected() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Category category = new Category("");;
        });

        assertTrue(exception.getMessage().contains("Invalid Category Name"));
    }

    @Test
    public void shouldNotCreateACategoryWithOnlyNumbers_IllegalArgumentExceptionExpected() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Category category = new Category("333");;
        });

        assertTrue(exception.getMessage().contains("Invalid Category Name"));
    }

    @Test
    public void shouldNotCreateACategoryWithOnlySpecialCharacters_IllegalArgumentExceptionExpected() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Category category = new Category("***");;
        });

        assertTrue(exception.getMessage().contains("Invalid Category Name"));
    }




//    @Test
//    void createAndSaveCategory() {
//        Category category = new Category("Maths");
//
//        assertTrue();
//    }


    /*// Arrange
    when(country.getCode()).thenReturn("PT");
    when(country.getName()).thenReturn("Portugal");

    when( countryFactory.createCountry("PT", "Portugal")).thenReturn(country);

    when(countryRepository.save( country )).thenReturn(country);

    // Act
    Country country = countryService.createAndSaveCountry("PT", "Portugal");

    String code = country.getCode();
    String name = country.getName();

    // Assert
    assertEquals(code, "PT");
    assertEquals(name, "Portugal");
}*/


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