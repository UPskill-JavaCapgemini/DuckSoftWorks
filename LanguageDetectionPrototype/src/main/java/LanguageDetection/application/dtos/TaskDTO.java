package LanguageDetection.application.dtos;

import LanguageDetection.domain.ValueObjects.Language;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class TaskDTO extends RepresentationModel<TaskDTO> {

    @Getter
    Language language;

    @Getter
    String inputText;

    public TaskDTO(String inputText, Language language) {
        this.inputText=inputText;
        this.language = language;
    }
}