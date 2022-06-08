package LanguageDetection.domain.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;
import LanguageDetection.domain.util.BusinessValidation;

import javax.persistence.*;

@Embeddable
@Table
public class CategoryName implements ValueObject {

    /**
     * getter for the cathegory's description as a string
     */
    String categoryName;

    public CategoryName() {
    }

    /**
     * constructor for the Category Description with validations
     * it prevents the category to be created with only numbers, only spaces or without any input.
     */
    public CategoryName(String categoryName) {
        if (BusinessValidation.isOnlyNumbers(categoryName) || categoryName.isBlank() || categoryName.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
