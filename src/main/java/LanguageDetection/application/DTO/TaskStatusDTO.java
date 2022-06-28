package LanguageDetection.application.DTO;

import LanguageDetection.domain.model.ValueObjects.TaskStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;


@NoArgsConstructor
/**
 * Represents a TaskStatusDTO containing the id and status for a Task
 */
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