package LanguageDetection.application.services;

import LanguageDetection.domain.DomainService.ILanguageDetector;
import LanguageDetection.domain.DomainService.LanguageAnalyzer;
import LanguageDetection.domain.entities.Task;
import LanguageDetection.infrastructure.repositories.TaskRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


import java.util.Optional;
import java.util.concurrent.Callable;

@Async
@Slf4j
@Component
public class LanguageDetectionService implements Callable<String> {
    @Getter
    @Setter
    private Task taskRepo;

    ILanguageDetector languageInterface;

    @Setter
    TaskRepository taskRepository;

    @Override
    public String call() throws Exception {
        LanguageAnalyzer lang = languageInterface.createAnalizer();
        String language = lang.analyze(taskRepo.getInputUrl().getUrl());
        Optional<Task> checkTask = taskRepository.findById(taskRepo.getId());

        if (checkTask.isPresent() && checkTask.get().getCurrentStatus().toString().equals("Processing")) {
            Task taskCompleted = new Task(taskRepo, language);
            taskRepository.saveTask(taskCompleted);
        }

        return language;
    }
}
