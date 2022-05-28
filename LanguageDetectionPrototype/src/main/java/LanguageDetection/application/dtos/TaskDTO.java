package LanguageDetection.application.dtos;

import LanguageDetection.domain.ValueObjects.Language;
import LanguageDetection.domain.entities.example.Task;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class TaskDTO extends RepresentationModel<TaskDTO> {

    @Getter
    Language language;

    public TaskDTO(Task language) {
        this.language = language.getLang();
    }
}