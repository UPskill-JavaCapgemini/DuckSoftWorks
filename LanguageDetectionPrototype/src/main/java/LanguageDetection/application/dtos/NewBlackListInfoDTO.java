package LanguageDetection.application.dtos;

import LanguageDetection.domain.ValueObjects.InputUrl;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NewBlackListInfoDTO {
    @Getter
    @Setter
    InputUrl url;
}
