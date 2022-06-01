package LanguageDetection.application.controllers;


import LanguageDetection.application.dtos.AnalysisDTO;
import LanguageDetection.application.dtos.NewAnalysisInfoDTO;
import LanguageDetection.application.services.AnalysisService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RestController
@RequestMapping(path = "/LanguageDetection")
public class AnalysisController {

    @Autowired
    private AnalysisService service;



    /**
     * This method receives a NewTaskInfoDTO object, that is automatically created from a JSON by SpringFrameWork, the information is extracted
     * from it and passed to an instance of TaskService that returns a TaskDTO with the info to be passed to the user in the ResponseEntity object.
     *
     * @param info receives a JSON file that is automatically transformed into a NewTaskInfoDTO object
     * @throws ParseException thrown by QueryParser it can occur when fail to parse a String that is ought to have a special format
     * @throws IOException thrown by IndexReader class if some sort of I/O problem occurred
     */


    @PostMapping("")
    public ResponseEntity<Object> createAnalysisFromInput(@RequestBody NewAnalysisInfoDTO info) throws ParseException, IOException {
        AnalysisDTO task = service.analyzeLanguage(info);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }


/*    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Mono<TaskDTO>> getById(@PathVariable Long id) {
        Mono<TaskDTO> monoTask = service.getLanguage(id);
        return new ResponseEntity<>(monoTask, HttpStatus.OK);
    }*/
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

