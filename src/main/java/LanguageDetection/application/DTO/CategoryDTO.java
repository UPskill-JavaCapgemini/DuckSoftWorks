package LanguageDetection.application.DTO;

import LanguageDetection.domain.model.Category;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
public class CategoryDTO {

    @Getter
    String category;


    public CategoryDTO(Category category) {
        this.category = category.getCategoryName().getCategoryName();
    }
}
