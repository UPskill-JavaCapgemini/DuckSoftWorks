
package LanguageDetection.application.controllers;


import LanguageDetection.application.DTO.*;
import LanguageDetection.application.services.TaskService;
import LanguageDetection.domain.entities.Task;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Controller
@RestController
@RequestMapping(path = "/LanguageDetection")
public class TaskController {

    @Autowired
    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    /**
     * This method receives a NewTaskInfoDTO object, that is automatically created from a JSON by SpringFrameWork, the information is extracted
     * from it and passed to an instance of TaskService that returns a TaskDTO with the info to be passed to the user in the ResponseEntity object.
     *
     * @param info receives a JSON file that is automatically transformed into a NewTaskInfoDTO object
     * @throws ParseException thrown by QueryParser it can occur when fail to parse a String that is ought to have a special format
     * @throws IOException    thrown by IndexReader class if some sort of I/O problem occurred
     */


    @PostMapping("")
    public ResponseEntity<Object> createAndSaveTask(@RequestBody NewTaskInfoDTO info) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        Optional<TaskStatusDTO> taskCreated = service.createAndSaveTask(info);
        if (taskCreated.isPresent()) {
            return new ResponseEntity<>(taskCreated.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity("Unable to create task", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getAllCategoryByNameStatus(
            @RequestParam (name = "categoryName", required = false) CategoryNameDTO categoryName,
            @RequestParam (name = "status", required = false) StatusDTO status) {

        List<Task> tasks = new ArrayList<>();

        if (status == null && categoryName == null)
            tasks = service.findAllTasks();
        else if (status != null && categoryName == null){
            tasks = service.findByStatusContaining(status);}
        else if (status == null){
            tasks = service.findByCategoryContaining(categoryName); }
        else {
            tasks = service.findByStatusContainingAndCategoryContaining(status, categoryName);
        }
        return new ResponseEntity<>(tasks.toString(), HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<Object> cancelAnalysisThread(@RequestBody NewCancelThreadDTO id) throws MalformedURLException {
        Optional<Task> cancelTask = service.cancelTaskAnalysis(id);
        if(cancelTask.isPresent()){
            return new ResponseEntity<>(cancelTask.get().toString(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}


