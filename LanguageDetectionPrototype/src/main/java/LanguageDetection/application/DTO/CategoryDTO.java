package LanguageDetection.application.DTO;

import LanguageDetection.domain.entities.Category;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@EqualsAndHashCode
public class CategoryDTO {

    Category category;


    public CategoryDTO(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category.toString();
    }
}
