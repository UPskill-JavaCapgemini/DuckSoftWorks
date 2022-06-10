package LanguageDetection.Domain;

import LanguageDetection.domain.entities.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    void shouldCreateACategoryWithCorrectAttributes() {
        Category category = new Category("Arts");

        assertEquals(category.getCategoryName().toString(), "Arts");
    }

    @Test
    void shouldCreateACategoryWithCorrectAttributesIncludingSpaces() {
        Category category = new Category("Performing Arts");

        assertEquals(category.getCategoryName().toString(), "Performing Arts");
    }

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
}
