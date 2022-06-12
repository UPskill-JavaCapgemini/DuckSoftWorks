package LanguageDetection.application.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CategoryNameDTO {
    @Getter
    String categoryName;

    @Override
    public String toString() {
        return categoryName;
    }
}
