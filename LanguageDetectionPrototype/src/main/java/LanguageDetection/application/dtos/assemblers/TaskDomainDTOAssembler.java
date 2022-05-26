package LanguageDetection.application.dtos.assemblers;

import LanguageDetection.application.dtos.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TaskDomainDTOAssembler {

    private TaskDomainDTOAssembler() {
    }

    public TaskDTO toDTO(String language, Date date) {
        return new TaskDTO(language, date);
    }
}