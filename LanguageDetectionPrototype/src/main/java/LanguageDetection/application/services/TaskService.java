package LanguageDetection.application.services;


import LanguageDetection.application.dtos.NewTaskInfoDTO;
import LanguageDetection.application.dtos.TaskDTO;
import LanguageDetection.application.dtos.assemblers.TaskDomainDTOAssembler;
import LanguageDetection.domain.ValueObjects.Language;
import LanguageDetection.domain.entities.example.Task;
import LanguageDetection.domain.factories.TaskFactory;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;



@Service
public class TaskService {

    @Autowired
    TaskFactory taskFactory;

    @Autowired
    TaskDomainDTOAssembler taskDomainDTOAssembler;

    @Autowired
    AnalyzerService analyzerService;



    public TaskDTO createTask(NewTaskInfoDTO string) throws ParseException, IOException {
        String cleanedUp = cleanUpInputText(string.getText());
        String language = analyzerService.analyze(cleanedUp);
        Task task = taskFactory.createTask(string.getText(), Language.valueOf(language));
        TaskDTO taskDTO = taskDomainDTOAssembler.toDTO(task.getLang());
        return taskDTO;
    }


    private static String cleanUpInputText(String text) {
        return text.trim().toLowerCase(Locale.ROOT)
                .replaceAll("[^a-zA-Z\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u024F]", " ")
                //.replaceAll("\\p{P}", "") //PUNCTUATION
                //.replaceAll("\\p{N}", "") // NUMBERS
                .replaceAll("\\s+", " "); // MULTIPLE_WHITESPACE
    }
}


