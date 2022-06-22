package LanguageDetection.application.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
/**
 * Represents a CategoryNameDTO containing the CategoryName that was inputted by the user
 */
public class CategoryNameDTO {
    @Getter
    String categoryName;

}
