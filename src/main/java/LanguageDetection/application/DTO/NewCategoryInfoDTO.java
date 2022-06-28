package LanguageDetection.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
/**
 * Represents a NewCategoryInfoDTO info parsed from a JSON sent via HTTP request
 */
public class NewCategoryInfoDTO {

    @Getter
    String category;
}
