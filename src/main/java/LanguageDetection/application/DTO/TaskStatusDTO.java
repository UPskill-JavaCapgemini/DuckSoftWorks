package LanguageDetection.application.DTO;

import LanguageDetection.domain.model.Task;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class TaskStatusDTO extends RepresentationModel<TaskStatusDTO> {

    @Getter
    Long id;
    @Getter
    Task.TaskStatus status;


    public TaskStatusDTO(Long id, Task.TaskStatus status) {
        this.id = id;
        this.status = status;
    }
}