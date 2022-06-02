package LanguageDetection.application.dtos;

import LanguageDetection.domain.ValueObjects.URL;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NewTaskInfoDTO {
    @Getter @Setter
    URL url;
}
