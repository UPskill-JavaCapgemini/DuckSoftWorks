package LanguageDetection.domain.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;
import LanguageDetection.domain.util.BusinessValidation;

import javax.persistence.*;

import java.util.Objects;

import static LanguageDetection.domain.util.BusinessValidation.isOnlyNumbers;
import static LanguageDetection.domain.util.BusinessValidation.isOnlySpecialCharacters;

@Embeddable
public class CategoryName implements ValueObject {

    /**
     * getter for the category's name as a string
     */
    String categoryName;

    private static final String DEFAULT_CATEGORY = "Unassigned";

    protected CategoryName() {
    }

    /**
     * constructor for the Category Name with validations
     * it prevents the category name to be created with only special characters, only numbers and only spaces or without any input.
     */
    public CategoryName(String categoryName) {
       try {
           if (isOnlySpecialCharacters(categoryName) || isOnlyNumbers(categoryName) || categoryName.isBlank() || categoryName.isEmpty())
               this.categoryName = DEFAULT_CATEGORY;
           else{
            this.categoryName  = categoryName;
           }
       }catch (NullPointerException nullPointerException)
       {
           this.categoryName = DEFAULT_CATEGORY;
       }
}

    @Override
    public String toString() {
        return categoryName;
    }
}
