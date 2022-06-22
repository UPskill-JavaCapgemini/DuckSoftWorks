package LanguageDetection.application.DTO;

import LanguageDetection.domain.model.Task;
import LanguageDetection.domain.model.ValueObjects.TaskStatus;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class TaskStatusDTO extends RepresentationModel<TaskStatusDTO> {

    @Getter
    Long id;
    @Getter
    TaskStatus status;


    public TaskStatusDTO(Long id, TaskStatus status) {
        this.id = id;
        this.status = status;
    }
}