package LanguageDetection.application.DTO.DTOAssemblers;

import LanguageDetection.application.DTO.TaskStatusDTO;
import LanguageDetection.domain.entities.Task;
import org.springframework.stereotype.Service;


@Service
public class TaskDomainDTOAssembler {

    private TaskDomainDTOAssembler() {
    }

    public TaskStatusDTO toDTO(Task task) {
        return new TaskStatusDTO(task);
    }
}