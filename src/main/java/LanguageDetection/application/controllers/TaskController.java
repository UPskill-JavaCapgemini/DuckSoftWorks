package LanguageDetection.application.controllers;

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
 * Class responsible for communication with HTTP request with respective mappings
 *
 * @authors DuckSoftWorks Team
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
     * This method receives a NewTaskInfoDTO object, that is automatically created from a JSON by SpringFrameWork, the information is extracted
     * from it and passed to an instance of TaskService that returns a TaskDTO with the info to be passed to the user in the ResponseEntity object.
     *
     * @param info receives a JSON file that is automatically transformed into a NewTaskInfoDTO object
     * @return information of Processing status with HTTPStatus 201(Created) or String with error information of HTTPStatus 400(Bad Request) if some error occurred
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
     * This method returns a list of Tasks that was already persisted. It returns a list of all tasks if no arguments are passed to a
     * GET request. If status is sent and category name is not, returns a list of tasks with that status.
     * If category name is sent and status is not, returns a list of tasks with that category name.
     * If a category name and a status is sent, returns a list of tasks with the respective category and status.
     * @param categoryName - object of CategoryNameDTO which is created with Spring Boot automatically if passed in GET request query
     * @param status - object of StatusDTO which is created with Spring Boot automatically if passed in GET request query
     * @return String with a list of tasks and with HTTP Status 200 (OK)
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
     * If user wants to cancel the task language analysis, this method puts the Task in "Canceled" status and no language will be associated
     * with that task.
     * @param id object of NewCancelThreadDTO which is created automatically by Spring Boot if a Long id of a task is sent by JSON body.
     * @return String with information of the canceled task and with HTTPStatus Accepted(202) if an id valid is sent, or HTTPStatus NotFound(404) if an invalid id is sent.
     */

    @PostMapping(value = "/cancel", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> cancelAnalysisThread(@RequestBody NewCancelThreadDTO id) {
        Optional<TaskDTO> cancelTask = taskManagementService.cancelTaskAnalysis(id);
        if(cancelTask.isPresent()){
            return new ResponseEntity<>(cancelTask.get(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Invalid ID of Task", HttpStatus.NOT_FOUND);
        }
    }

}


