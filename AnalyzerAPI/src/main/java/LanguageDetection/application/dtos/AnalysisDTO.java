package LanguageDetection.application.dtos;

import LanguageDetection.domain.entities.example.Language;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class AnalysisDTO extends RepresentationModel<AnalysisDTO> {

    @Getter
    Language language;

    public AnalysisDTO(Language language) {
        this.language = language;
    }
}