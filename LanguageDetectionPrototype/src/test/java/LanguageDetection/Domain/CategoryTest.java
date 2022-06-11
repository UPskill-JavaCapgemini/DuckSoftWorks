package LanguageDetection.Domain;

import LanguageDetection.domain.entities.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    void shouldCreateACategoryWithCorrectAttributes() {
        //Arrange
        Category category = new Category("Arts");

        //Assert
        assertEquals(category.getCategoryName().toString(), "Arts");
    }

    @Test
    void shouldCreateACategoryWithCorrectAttributesIncludingSpaces() {
        //Arrange
        Category category = new Category("Performing Arts");

        //Assert
        assertEquals(category.getCategoryName().toString(), "Performing Arts");
    }

    @Test
    public void shouldNotCreateACategoryWithEmptyAttribute_IllegalArgumentExceptionExpected() {
        //Arrange / Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Category category = new Category("");;
        });

        //Assert
        assertTrue(exception.getMessage().contains("Invalid Category Name"));
    }

    @Test
    public void shouldNotCreateACategoryWithOnlyNumbers_IllegalArgumentExceptionExpected() {
        // Arrange / Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Category category = new Category("333");;
        });

        //Assert
        assertTrue(exception.getMessage().contains("Invalid Category Name"));
    }

    @Test
    public void shouldNotCreateACategoryWithOnlySpecialCharacters_IllegalArgumentExceptionExpected() {
        // Arrange / Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Category category = new Category("***");;
        });

        //Assert
        assertTrue(exception.getMessage().contains("Invalid Category Name"));
    }
}
