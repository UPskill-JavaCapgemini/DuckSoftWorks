package LanguageDetection.application.dtos.assemblers;

import LanguageDetection.application.dtos.TaskDTO;
import org.springframework.stereotype.Service;

@Service
public class TaskDomainDTOAssembler {

    private TaskDomainDTOAssembler() {
    }

    public TaskDTO toDTO (Long id, String name ) {
        return new TaskDTO(id, name);
    }
}