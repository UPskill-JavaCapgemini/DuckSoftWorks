package LanguageDetection.application.DTO;

import lombok.*;

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
