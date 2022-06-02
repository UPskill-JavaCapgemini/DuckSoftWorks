package LanguageDetection.application.DTO.DTOAssemblers;

import LanguageDetection.application.DTO.TaskDTO;
import LanguageDetection.domain.entities.Task;
import org.springframework.stereotype.Service;


@Service
public class TaskDomainDTOAssembler {

    private TaskDomainDTOAssembler() {
    }

    public TaskDTO toDTO(Task task) {
        return new TaskDTO(task);
    }
}