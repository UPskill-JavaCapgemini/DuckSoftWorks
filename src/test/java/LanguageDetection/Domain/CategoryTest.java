package LanguageDetection.Domain;

import LanguageDetection.domain.model.Category;
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
}
