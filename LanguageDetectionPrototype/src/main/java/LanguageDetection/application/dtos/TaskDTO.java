package LanguageDetection.application.dtos;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class TaskDTO extends RepresentationModel<TaskDTO> {

    @Getter
    Date date;

    @Getter
    String inputText;

    public TaskDTO(String inputText, Date date) {
        this.inputText=inputText;
        this.date = date;
    }
}