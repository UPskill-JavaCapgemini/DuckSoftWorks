package LanguageDetection.application.DTO;

import LanguageDetection.domain.entities.Task;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class TaskDTO extends RepresentationModel<TaskDTO> {

    @Getter
    Task.Language language;

    public TaskDTO(Task language) {
        this.language = language.getLanguage();
    }
}