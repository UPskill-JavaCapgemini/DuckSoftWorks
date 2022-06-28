package LanguageDetection.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
/**
 * Represents a NewBLackListDTO info parsed from a JSON sent via HTTP request
 */
public class NewBlackListInfoDTO {
    @Getter
    @Setter
    String url;
}
