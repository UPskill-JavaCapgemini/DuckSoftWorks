package LanguageDetection.application.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
/**
 * Represents a NewCategoryInfoDTO info parsed from a JSON sent via HTTP request
 */
public class NewCategoryInfoDTO {

    @Getter
    String category;
}
