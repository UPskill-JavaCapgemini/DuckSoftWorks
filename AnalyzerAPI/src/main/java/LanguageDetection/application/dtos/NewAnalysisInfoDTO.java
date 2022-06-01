package LanguageDetection.application.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NewAnalysisInfoDTO {
    @Getter @Setter
    String url;
}
