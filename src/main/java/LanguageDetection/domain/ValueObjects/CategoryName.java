package LanguageDetection.domain.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;
import LanguageDetection.domain.util.BusinessValidation;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

import java.util.Objects;

import static LanguageDetection.domain.util.BusinessValidation.isOnlyNumbers;
import static LanguageDetection.domain.util.BusinessValidation.isOnlySpecialCharacters;

@Embeddable
public class CategoryName implements ValueObject, Comparable<CategoryName> {

    private String categoryName;

    private static final String DEFAULT_CATEGORY = "Philosophy";

    /* For ORM purposes */
    protected CategoryName(){
        this.categoryName = null;
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

    public String getCategoryName() {
        return categoryName;
        //TODO: Can we use a getter here?
    }

    @Override
    public String toString() {
        return categoryName;
    }

    @Override
    public int compareTo(@NotNull CategoryName o) {
        return this.categoryName.compareTo(o.categoryName);
    }
}
