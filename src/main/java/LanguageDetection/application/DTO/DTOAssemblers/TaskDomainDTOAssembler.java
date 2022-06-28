package LanguageDetection.application.DTO.DTOAssemblers;

import LanguageDetection.application.DTO.TaskDTO;
import LanguageDetection.application.DTO.TaskStatusDTO;
import LanguageDetection.domain.model.Task;
import org.springframework.stereotype.Service;


@Service
/**
 * Represents the TaskDomainDTOAssembler. The assembler of either a TaskStatusDTO or a TaskDTO by input of a Task
 */
public class TaskDomainDTOAssembler {

    private TaskDomainDTOAssembler() {
    }

    public TaskStatusDTO toDTO(Task task) {
        return new TaskStatusDTO(task.getId(), task.getCurrentStatus());
    }

    public TaskDTO toCompleteDTO(Task task){
        return new TaskDTO(task.getId(), task.getDate(), task.getInputUrl(), task.getTaskResult(), task.getCurrentStatus(), task.getTimeOut(), task.getCategory());
    }
}