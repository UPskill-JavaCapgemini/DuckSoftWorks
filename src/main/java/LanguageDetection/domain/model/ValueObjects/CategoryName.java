package LanguageDetection.domain.model.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

import static LanguageDetection.domain.util.BusinessValidation.isOnlyNumbers;
import static LanguageDetection.domain.util.BusinessValidation.isOnlySpecialCharacters;

@Embeddable()
public class CategoryName implements ValueObject, Comparable<CategoryName> {

    private final String categoryName;

    private static final String DEFAULT_CATEGORY = "Philosophy";

    /* For ORM purposes */
    protected CategoryName() {
        this.categoryName = null;
    }

    /**
     * constructor for the Category Name with validations
     * it prevents the category name to be created with only special characters, only numbers and only spaces or without any input.
     */
    public CategoryName(String categoryName) {
        if (isOnlySpecialCharacters(categoryName) || isOnlyNumbers(categoryName) || categoryName.isBlank() || categoryName.isEmpty()){
            this.categoryName = DEFAULT_CATEGORY;
            throw new IllegalArgumentException("Impossible to create category name");
        }
        else {
            this.categoryName = categoryName;
        }
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return this.categoryName;
    }

    @Override
    public int compareTo(@NotNull CategoryName o) {
        return this.categoryName.compareTo(o.categoryName);
    }
}
