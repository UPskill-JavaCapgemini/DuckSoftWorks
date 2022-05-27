package LanguageDetection.application.dtos;

import LanguageDetection.domain.ValueObjects.Language;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class TaskDTO extends RepresentationModel<TaskDTO> {

    @Getter
    Language language;

    public TaskDTO(Language language) {
        this.language = language;
    }
}