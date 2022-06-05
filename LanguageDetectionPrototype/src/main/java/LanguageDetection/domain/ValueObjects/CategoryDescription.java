package LanguageDetection.domain.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;
import LanguageDetection.domain.util.BusinessValidation;
import lombok.Getter;

public class CategoryDescription implements ValueObject {

    /**
     * getter for the cathegory's description as a string
     */
    @Getter
    String categoryDescription;

    /**
     * constructor for the Category Description with validations
     * it prevents the category to be created with only numbers, only spaces or without any input.
     */
    public CategoryDescription(String categoryDescription) {
        if (BusinessValidation.isOnlyNumbers(categoryDescription) || categoryDescription.isBlank() || categoryDescription.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.categoryDescription = categoryDescription;
    }

    @Override
    public String toString() {
        return categoryDescription;
    }
}
