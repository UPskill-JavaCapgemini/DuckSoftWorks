package LanguageDetection.application.controllers;


import LanguageDetection.application.dtos.NewTaskInfoDTO;
import LanguageDetection.application.dtos.TaskDTO;
import LanguageDetection.application.services.TaskService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Controller
@RestController
@RequestMapping(path = "/LanguageDetection")
public class TaskController {

    @Autowired
    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }




/*    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Mono<TaskDTO>> getById(@PathVariable Long id) {
        Mono<TaskDTO> monoTask = service.getLanguage(id);
        return new ResponseEntity<>(monoTask, HttpStatus.OK);
    }*/

    @PostMapping("")
    public ResponseEntity<Object> createExample(@RequestBody NewTaskInfoDTO info) throws ParseException, IOException {
        TaskDTO task = service.createTask(info);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

/*
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Flux<TaskDTO>> getAll(@RequestParam(required = false, name = "name") String name) {
        Flux<TaskDTO> exampleDTOs;
        if (name != null)
            exampleDTOs = service.getExamplesByNameContaining(name);
        else
            exampleDTOs = service.getAllExamples();
        return new ResponseEntity<>(exampleDTOs, HttpStatus.OK);
    }

    @GetMapping("/custom")
    @ResponseBody
    public ResponseEntity<Flux<TaskDTO>> getAllCustomQuery(@RequestParam(required = false, name = "name") String name) {
        Flux<TaskDTO> exampleDTOs;
        if (name != null)
            exampleDTOs = service.getExamplesByNameContaining(name);
        else
            exampleDTOs = service.getAllExamples();
        return new ResponseEntity<>(exampleDTOs, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<Object> createExample(@RequestBody NewExampleInfoDTO info) {
        TaskDTO example = service.createAndSaveExample(info.getName());
        return new ResponseEntity<>(example, HttpStatus.CREATED);
    }*/
}

