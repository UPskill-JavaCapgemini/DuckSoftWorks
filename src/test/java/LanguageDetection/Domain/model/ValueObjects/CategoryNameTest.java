package LanguageDetection.Domain.model.ValueObjects;

import LanguageDetection.domain.model.ValueObjects.BlackListUrl;
import LanguageDetection.domain.model.ValueObjects.CategoryName;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class CategoryNameTest {

    @Test
    public void ensureCategoryNameIsNotCreatedWithOnlyNumbers(){
        String categoryName = "123456789";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new CategoryName(categoryName);
        });

        Assert.assertEquals(illegalArgumentException.getMessage(), "Impossible to create category name");
    }

    @Test
    public void ensureCategoryNameIsNotCreatedWithOnlySpecialCharacters(){
        String categoryName = "$%&/(/&%&/(/&%$))#$€";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new CategoryName(categoryName);
        });

        Assert.assertEquals(illegalArgumentException.getMessage(), "Impossible to create category name");
    }

    @Test
    public void ensureCategoryNameIsNotCreatedWithSpacesOnly(){
        String categoryName = "                               ";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new CategoryName(categoryName);
        });

        Assert.assertEquals(illegalArgumentException.getMessage(), "Impossible to create category name");
    }

    @Test
    public void ensureCategoryNameIsNotCreatedWhenEmpty(){
        String categoryName = "";

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            new CategoryName(categoryName);
        });

        Assert.assertEquals(illegalArgumentException.getMessage(), "Impossible to create category name");
    }

    @Test
    public void ensureCategoryNameIsCreatedWithOneWord(){
        CategoryName categoryName = new CategoryName("Politics");

        assertEquals(categoryName.toString(), "Politics");
    }

    @Test
    public void ensureCategoryNameIsCreatedWithTwoWords(){
        CategoryName categoryName = new CategoryName("Visual Arts");

        assertEquals(categoryName.toString(), "Visual Arts");
    }

    @Test
    public void ensureCategoryNameIsCreatedWithWordAndNumber(){
        CategoryName categoryName = new CategoryName("Sports2");

        assertEquals(categoryName.toString(), "Sports2");
    }

    @Test
    public void ensureCategoryNameIsCreatedWithLettersAndSpecialCharacters(){
        CategoryName categoryName = new CategoryName("L€€T");

        assertEquals(categoryName.getCategoryName(), "L€€T");
    }

    @Test
    public void ensureThatCategoriesWithSameNameAreEqual(){
        CategoryName categoryName1 = new CategoryName("Sports2");
        CategoryName categoryName2 = new CategoryName("Sports2");

        assertEquals(categoryName1.compareTo(categoryName2), 0);

    }

}