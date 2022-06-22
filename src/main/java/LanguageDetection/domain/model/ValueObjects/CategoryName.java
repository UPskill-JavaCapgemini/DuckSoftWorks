package LanguageDetection.domain.model.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

import static LanguageDetection.domain.util.BusinessValidation.isOnlyNumbers;
import static LanguageDetection.domain.util.BusinessValidation.isOnlySpecialCharacters;

/**
 * Represents the CategoryName of a Category. Assumes a default value of "Philosophy".
 * It validates if the provided value for the name is not made only of special characters, numbers, spaces or blank spaces
 */

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

    /**
     * This method returns a String with the Category value
     *
     * @return a String with a CategoryName value
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * This method represents a CategoryName textually
     * Returns a String with the CategoryName information
     *
     * @return a String with a textual representation of the CategoryName
     */
    @Override
    public String toString() {
        return this.categoryName;
    }

    /**
     * This method compares a CategoryName with another CategoryName
     * Returns
     *
     * @param o the other CategoryName used for comparison with this CategoryName
     * @return an int representing the difference between lexicographical order of the string values based on unicode. Used for sorting purposes.
     */
    @Override
    public int compareTo(@NotNull CategoryName o) {
        return this.categoryName.compareTo(o.categoryName);
    }
}
