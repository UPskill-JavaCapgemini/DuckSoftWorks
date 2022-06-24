package LanguageDetection.application.DTO;

import LanguageDetection.domain.model.Category;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
/**
 * Represents a CategoryDTO containing the Category that was inputted by the admin
 */
public class CategoryDTO {

    @Getter
    String category;


    public CategoryDTO(Category category) {
        this.category = category.getCategoryName().getCategoryName();
    }
}
