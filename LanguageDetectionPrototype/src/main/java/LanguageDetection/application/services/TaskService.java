package LanguageDetection.application.services;


import LanguageDetection.application.dtos.NewTaskInfoDTO;
import LanguageDetection.application.dtos.TaskDTO;
import LanguageDetection.application.dtos.assemblers.TaskDomainDTOAssembler;
import LanguageDetection.domain.DomainService.AnalyzerService;
import LanguageDetection.domain.entities.Task;
import LanguageDetection.domain.factories.TaskFactory;
import LanguageDetection.infrastructure.repositories.TaskRepository;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * Represents the task service responsible for creating a task.
 *
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

    @Autowired
     TaskRepository taskRepository;


    /**
     * Creates a new task with a NewTaskInfoDTO received by parameter.
     * Cleans up the input text by calling cleanUpInputText() and
     * analyzes it with the service analyzer.
     *
     * @param userInput the string containing the text within NewTaskInfoDTO.
     * @return TaskDTO assembled by taskDomainDTOAssembler.
     * @throws ParseException - Signals that an error has been reached unexpectedly in the QueryParse
     * @throws IOException    - thrown by IndexReader class if some sort of I/O problem occurred
     */
    public TaskDTO createTask(NewTaskInfoDTO userInput) throws ParseException, IOException {
//        String cleanedUp = cleanUpInputText(string.getText());
//        String language = analyzerService.analyze(cleanedUp);
        Task task = taskFactory.createTask(userInput.getUrl(), userInput.getCategory(), userInput.getTimeOut());
        Task taskRepo = taskRepository.save(task);
        return taskDomainDTOAssembler.toDTO(taskRepo);
    }

}


