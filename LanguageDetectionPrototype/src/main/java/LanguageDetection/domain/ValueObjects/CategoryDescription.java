package LanguageDetection.domain.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;
import LanguageDetection.domain.util.BusinessValidation;
import lombok.Getter;

public class CategoryDescription implements ValueObject {

    @Getter
    String categoryDescription;

    public CategoryDescription(String categoryDescription) {
        if (BusinessValidation.isOnlyNumbers(categoryDescription)){
            throw new IllegalArgumentException();
        }
        this.categoryDescription = categoryDescription;
    }
}
