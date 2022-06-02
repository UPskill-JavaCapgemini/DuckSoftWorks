package LanguageDetection.application.DTO;

import LanguageDetection.domain.ValueObjects.InputUrl;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NewBlackListInfoDTO {
    @Getter
    @Setter
    String url;
}
