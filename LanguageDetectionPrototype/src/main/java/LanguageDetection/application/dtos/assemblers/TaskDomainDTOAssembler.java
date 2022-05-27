package LanguageDetection.application.dtos.assemblers;

import LanguageDetection.application.dtos.TaskDTO;
import LanguageDetection.domain.ValueObjects.Language;
import org.springframework.stereotype.Service;


@Service
public class TaskDomainDTOAssembler {

    private TaskDomainDTOAssembler() {
    }

    public TaskDTO toDTO(Language language) {
        return new TaskDTO(language);
    }
}