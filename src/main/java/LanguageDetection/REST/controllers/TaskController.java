package LanguageDetection.REST.controllers;

import LanguageDetection.application.DTO.*;
import LanguageDetection.application.services.TaskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Represents the REST Controller for Task related functionalities
 */
@Controller
@CrossOrigin(origins = "http://localhost:5500/", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping(path = "/LanguageDetection")
public class TaskController {

    @Autowired
    private TaskManagementService taskManagementService;

    public TaskController(TaskManagementService taskManagementService) {
        this.taskManagementService = taskManagementService;
    }

    /**
     *  This method attempts to create and save a Task with the information provided by the user
     *
     * @param info the NewTaskInfoDTO containing information about the task to be created and saved
     * @return a ResponseEntity that holds information about wether creating and saving the task was successfully, or not
     */
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> createAndSaveTask(@RequestBody NewTaskInfoDTO info) {
        Optional<TaskStatusDTO> taskCreated = taskManagementService.createAndSaveTask(info);
        if (taskCreated.isPresent()) {
            return new ResponseEntity<>(taskCreated.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity("Unable to create task", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method fetches information for all tasks created  by a given user and persisted in the database.Returns a list containing them
     * Tasks can be fetched using no filters, Category only, Status only, or both
     *
     * @param categoryName the CategoryNameDTO containing the information about the Category to be used for searching tasks
     * @param status the StatusDTO containing the information about the status to be used for searching for tasks
     * @return a ResponseEntity that holds information of a list with all tasks searched by parameter(s) if there are any,
     * an empty list if there are no tasks searched by the given parameter(s)
     */
    @GetMapping("")
    @ResponseBody
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> getAllTasksFilter(
            @RequestParam (name = "categoryName", required = false) CategoryNameDTO categoryName,
            @RequestParam (name = "status", required = false) StatusDTO status) {

        List<TaskDTO> tasks;

        if (status == null && categoryName == null)
            tasks = taskManagementService.getAllTasks();
        else if (status != null && categoryName == null){
            tasks = taskManagementService.findByStatusContaining(status);}
        else if(status == null) {
            tasks = taskManagementService.findByCategoryNameContaining(categoryName); }
        else {
            tasks = taskManagementService.findByStatusContainingAndCategoryContaining(status, categoryName);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    /**
     * This method attempts to cancel an ongoing language analysis process for a user specific Task, if this task is processing
     *
     * @param id the NewCancelThreadDTO containing the information about the task to be cancelled
     * @return a ResponseEntity that holds information about if cancelling the task language analysis was successful , or not
     */

    @PostMapping(value = "/cancel", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> cancelTaskAnalysis(@RequestBody NewCancelThreadDTO id) {
        Optional<TaskStatusDTO> cancelTask = taskManagementService.cancelTaskAnalysis(id);
        if(cancelTask.isPresent()){
            return new ResponseEntity<>(cancelTask.get(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Invalid ID of Task", HttpStatus.NOT_FOUND);
        }
    }

}


