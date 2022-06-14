package LanguageDetection.application.DTO;

import LanguageDetection.domain.entities.Task;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class TaskStatusDTO extends RepresentationModel<TaskStatusDTO> {

    @Getter
    Task.TaskStatus status;

    public TaskStatusDTO(Task status) {
        this.status = status.getCurrentStatus();
    }
}