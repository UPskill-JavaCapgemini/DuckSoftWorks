
package LanguageDetection.application.services;

import LanguageDetection.application.DTO.*;
import LanguageDetection.application.DTO.DTOAssemblers.TaskDomainDTOAssembler;
import LanguageDetection.domain.DomainService.TaskService;
import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.Task;
import LanguageDetection.domain.model.ValueObjects.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Represents the TaskManagementService. The applicational service for Task functionalities
 */
@Service
public class TaskManagementService {


    @Autowired
    TaskDomainDTOAssembler taskDomainDTOAssembler;

    @Autowired
    TaskService taskService;


    /**
     * This method attempts to create and save a Task with the information provided by the user with user privileges
     *
     * @param userInput the NewTaskInfoDTO containing the information about the Task to be created and saved
     * @return TaskDTO assembled through the TaskDomainDTOAssembler wrapped in an Optional if successful or an empty Optional if not
     */
    public Optional<TaskStatusDTO> createAndSaveTask(NewTaskInfoDTO userInput) {
        try {
            Optional<Task> opTask = taskService.createAndSaveTask(userInput.getUrl(), userInput.getTimeOut(), userInput.getCategory());
            if (opTask.isPresent()) {
                return Optional.of(taskDomainDTOAssembler.toDTO(opTask.get()));
            }
            return Optional.empty();
        } catch (MalformedURLException e) {
            return Optional.empty();
        }
    }

    /**
     * This method fetches information for all tasks persisted in the database and returns a list of TaskDTO
     * containing them if there are any, or an empty list if no tasks were persisted in the database
     *
     * @return a list of TaskDTO if tasks were found, an empty list if no tasks were found
     */
    public List<TaskDTO> getAllTasks() {
        List<Task> listAllTasks = taskService.findAllTasks();
        List<TaskDTO> taskDTOList = new ArrayList<>();

        for (Task task : listAllTasks) {
            TaskDTO assemble = taskDomainDTOAssembler.toCompleteDTO(task);
            taskDTOList.add(assemble);
        }

        return taskDTOList;
    }

    /**
     * This method fetches information for all tasks persisted in the database by status and returns a list of TaskDTO
     * containing them if there are any, or an empty list if no tasks were persisted in the database
     *
     * @param inputStatus the StatusDTO containing information about the TaskStatus to be used for task search
     * @return a list of TaskDTO if tasks were found, an empty list if no tasks were found
     */
    public List<TaskDTO> findByStatusContaining(StatusDTO inputStatus) {
        TaskStatus status = TaskStatus.valueOf(inputStatus.getStatus());
        List<Task> listTasksByStatus = taskService.findByStatusContaining(status);

        List<TaskDTO> taskDTOList = new ArrayList<>();

        for (Task task : listTasksByStatus) {
            TaskDTO assemble = taskDomainDTOAssembler.toCompleteDTO(task);
            taskDTOList.add(assemble);
        }
        return taskDTOList;
    }

    /**
     * This method fetches information for all tasks persisted in the database by CategoryName and returns a list of TaskDTO
     * containing them if there are any, or an empty list if no tasks were persisted in the database
     *
     * @param catName the CategoryNameDTO containing information about the CategoryName to be used for task search
     * @return a list of TaskDTO if tasks were found, an empty list if no tasks were found
     */
    public List<TaskDTO> findByCategoryNameContaining(CategoryNameDTO catName) {
        Category category = new Category(catName.getCategoryName());
        List<Task> listTasksByCategory = taskService.findByCategoryNameContaining(category);

        List<TaskDTO> taskDTOList = new ArrayList<>();

        for (Task task : listTasksByCategory) {
            TaskDTO assemble = taskDomainDTOAssembler.toCompleteDTO(task);
            taskDTOList.add(assemble);
        }

        return taskDTOList;
    }

    /**
     * This method fetches information for all tasks persisted in the database by TaskStatus and CategoryName and returns a list of TaskDTO
     * containing them if there are any, or an empty list if no tasks were persisted in the database
     *
     * @param inputStatus the StatusDTO containing information about the TaskStatus to be used for task search
     * @param inputCategory the CategoryNameDTO containing information about the CategoryName to be used for task search
     * @return a list of TaskDTO if tasks were found, an empty list if no tasks were found
     */
    public List<TaskDTO> findByStatusContainingAndCategoryContaining(StatusDTO inputStatus, CategoryNameDTO inputCategory) {
        TaskStatus status = TaskStatus.valueOf(inputStatus.getStatus());
        Category category = new Category(inputCategory.getCategoryName());

        List<Task> listTasksByStatusAndByCategory = taskService.findByStatusAndByCategoryContaining(status, category);

        List<TaskDTO> taskDTOList = new ArrayList<>();

        for (Task task : listTasksByStatusAndByCategory) {
            TaskDTO assemble = taskDomainDTOAssembler.toCompleteDTO(task);
            taskDTOList.add(assemble);
        }
        return taskDTOList;
    }

    /**
     * This method attempts to cancel an ongoing language analysis process for a user specific Task, if this task is processing
     *
     * @param id the NewCancelThreadDTO containing the information about the task to be cancelled
     * @return  TaskDTO assembled through the TaskDomainDTOAssembler wrapped in an Optional if cancelling the Task was successful
     * An empty Optional if not
     */

    public Optional<TaskStatusDTO> cancelTaskAnalysis(NewCancelThreadDTO id) {

        Optional<Task> opTask = taskService.cancelTaskAnalysis(id.getId());
        if (opTask.isPresent()) {
            return Optional.of(taskDomainDTOAssembler.toDTO(opTask.get()));
        }
        return Optional.empty();
    }
}

