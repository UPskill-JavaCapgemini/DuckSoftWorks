
package LanguageDetection.application.services;

import LanguageDetection.application.DTO.*;

import LanguageDetection.application.DTO.NewTaskInfoDTO;
import LanguageDetection.application.DTO.TaskStatusDTO;

import LanguageDetection.application.DTO.DTOAssemblers.TaskDomainDTOAssembler;
import LanguageDetection.domain.DomainService.ILanguageDetector;
import LanguageDetection.domain.model.ValueObjects.TaskResult;
import LanguageDetection.infrastructure.repositories.TaskRepository;
import LanguageDetection.infrastructure.repositories.analyzer.LanguageDetectionService;
import LanguageDetection.domain.model.ValueObjects.CategoryName;
import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.ITaskRepository;
import LanguageDetection.domain.model.Task;
import LanguageDetection.domain.model.TaskFactory;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;



/**
 * Represents the task service responsible for handling all methods which interacts with a task.
 *
 * @author DuckSoftWorks Team
 */
@Service
public class TaskService {



    @Autowired
    TaskDomainDTOAssembler taskDomainDTOAssembler;

    @Autowired
    ITaskRepository iTaskRepository;

    @Autowired
    TaskFactory taskFactory;

    @Autowired
    ILanguageDetector iLanguageDetector;


    /**
     * Creates a new task with a NewTaskInfoDTO received by parameter.
     * Before task creation it is checked if URL from input is on BlackList or not.
     *
     * @param userInput the string containing the text within NewTaskInfoDTO.
     * @return TaskStatusDTO assembled by taskDomainDTOAssembler, with information of Status when created(Processing) or empty if url is on blacklist and unable to crate
     */
    public Optional<TaskStatusDTO> createAndSaveTask(NewTaskInfoDTO userInput) {

        try {
            Optional<Task> opCreatedTask = taskFactory.createTask(userInput.getUrl(),userInput.getTimeOut(), userInput.getCategory());
            if (opCreatedTask.isPresent())
            {
                Task savedTask = this.iTaskRepository.saveTask(opCreatedTask.get());

                iLanguageDetector.languageAnalysis(savedTask);
                return Optional.of(taskDomainDTOAssembler.toDTO(savedTask));
            }
        } catch (MalformedURLException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    /**
     * Fetch all tasks found in database
     * @return List of TaskDTO with all information
     */
    public List<TaskDTO> getAllTasks() {
        List<Task> listAllTasks = iTaskRepository.findAllTasks();
        List<TaskDTO> taskDTOList = new ArrayList<>();

        for (Task task : listAllTasks) {
            TaskDTO assemble = taskDomainDTOAssembler.toCompleteDTO(task);
            taskDTOList.add(assemble);
        }

        return taskDTOList;
    }

    /**
     * Fetch all tasks found in database with the status passed in StatusDTO instance
     * @param inputStatus StatusDTO object with string of status that want to be searched
     * @return List of TaskDTO with all information of task that has status the same as String inside StatusDTO instance
     */
    public List<TaskDTO> findByStatusContaining(StatusDTO inputStatus) {
        Task.TaskStatus status = Task.TaskStatus.valueOf(inputStatus.getStatus());
        List<Task> listTasksByStatus = iTaskRepository.findByStatusContaining(status);

        List<TaskDTO> taskDTOList = new ArrayList<>();

        for (Task task : listTasksByStatus) {
            TaskDTO assemble = taskDomainDTOAssembler.toCompleteDTO(task);
            taskDTOList.add(assemble);
        }
        return taskDTOList;
    }

    /**
     * Fetch all tasks found in database with the category passed in CategoryNameDTO instance
     * @param catName CategoryNameDTO object with string of category name that want to be searched
     * @return List of TaskDTO with all information of task that has category name the same as String inside CategoryNameDTO instance
     */
    public List<TaskDTO> findByCategoryNameContaining(CategoryNameDTO catName) {
        Category category = new Category(catName.getCategoryName());
        List<Task> listTasksByCategory = iTaskRepository.findByCategoryNameContaining(category);

        List<TaskDTO> taskDTOList = new ArrayList<>();

        for (Task task : listTasksByCategory) {
            TaskDTO assemble = taskDomainDTOAssembler.toCompleteDTO(task);
            taskDTOList.add(assemble);
        }

        return taskDTOList;
    }

    /**
     * Fetch all tasks found in database with the category and status passed in CategoryNameDTO instance and StatusDTO instance
     * @param inputStatus StatusDTO object with string of status that want to be searched
     * @param inputCategory CategoryNameDTO object with string of category name that want to be searched
     * @return List of TaskDTO with all information of task that has category name the same as String inside CategoryNameDTO instance and
     * status the same as string inside StatusDTO instance
     */
    public List<TaskDTO> findByStatusContainingAndCategoryContaining(StatusDTO inputStatus, CategoryNameDTO inputCategory) {
        Task.TaskStatus status = Task.TaskStatus.valueOf(inputStatus.getStatus());
        Category category = new Category(inputCategory.getCategoryName());

        List<Task> listTasksByStatusAndByCategory = iTaskRepository.findByStatusAndByCategoryContaining(status, category);

        List<TaskDTO> taskDTOList = new ArrayList<>();

        for (Task task : listTasksByStatusAndByCategory) {
            TaskDTO assemble = taskDomainDTOAssembler.toCompleteDTO(task);
            taskDTOList.add(assemble);
        }
        return taskDTOList;
    }

    /**
     * Handles the cancellation process of a language analysis from user input
     * @param id if of a task that user wants to cancel
     * @return TaskDTO instance with all information if a task is canceled or empty if that id does not correspond to one task
     */
    public Optional<TaskDTO> cancelTaskAnalysis(NewCancelThreadDTO id) {
        Optional<Task> optionalTask = iTaskRepository.findByTaskId(id.getId());
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            if(task.cancelTask()){
                iTaskRepository.saveTask(task);
                return Optional.of(taskDomainDTOAssembler.toCompleteDTO(task));
            }
        }
        return Optional.empty();
    }
}

