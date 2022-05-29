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


/**
 * Represents the task service responsible for creating a task.
 * @author DuckSoftWorks
 */
@Service
public class TaskService {

    @Autowired
    /**
     * The taskFactory.
     */
    TaskFactory taskFactory;


    @Autowired
    /**
     * The domain DTO assembler for a task.
     */
    TaskDomainDTOAssembler taskDomainDTOAssembler;


    @Autowired
    /**
     * The Analyzer service.
     */
    AnalyzerService analyzerService;


    /**
     * Creates a new task with a NewTaskInfoDTO received by parameter.
     * Cleans up the input text by calling cleanUpInputText() and
     * analyzes it with the service analyzer.
     *
     * @throws ParseException - Signals that an error has been reached unexpectedly in the QueryParse
     * @throws IOException - thrown by IndexReader class if some sort of I/O problem occurred
     * @param string the string containing the text within NewTaskInfoDTO.
     * @return  TaskDTO assembled by taskDomainDTOAssembler.
     */
    public TaskDTO createTask(NewTaskInfoDTO string) throws ParseException, IOException {
        String cleanedUp = cleanUpInputText(string.getText());
        String language = analyzerService.analyze(cleanedUp);
        Task task = taskFactory.createTask(string.getText(), Language.valueOf(language));
        return taskDomainDTOAssembler.toDTO(task);
    }

    /**
     * Cleans up the string received via input.
     * Strips the string of multiple whitespaces through the use of a regex.
     *
     * @param text the string that is cleaned up with the regex.
     * @return  the cleaned up text.
     */
    private static String cleanUpInputText(String text) {
        return text.trim().toLowerCase(Locale.ROOT)
                .replaceAll("[^a-zA-Z\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u024F]", " ")
                //.replaceAll("\\p{P}", "") //PUNCTUATION
                //.replaceAll("\\p{N}", "") // NUMBERS
                .replaceAll("\\s+", " "); // MULTIPLE_WHITESPACE
    }
}


