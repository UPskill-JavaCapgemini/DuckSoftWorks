package LanguageDetection.application.services;


import LanguageDetection.application.dtos.assemblers.TaskDomainDTOAssembler;

import LanguageDetection.domain.factories.ITaskFactory;
import LanguageDetection.infrastructure.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskService {

    @Autowired
    ITaskFactory taskFactory;

    @Autowired
    TaskDomainDTOAssembler taskDomainDTOAssembler;

    @Autowired
    TaskRepository taskRepository;







    // todo: put in a second repository
//    private WebClient webClient() {
//        WebClient webClient = WebClient.builder()
//                .baseUrl("http://localhost:8080")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
//                .clientConnector(new ReactorClientHttpConnector(HttpClient.create(ConnectionProvider.newConnection())))
//                .build();
//
//        return webClient;
    }


