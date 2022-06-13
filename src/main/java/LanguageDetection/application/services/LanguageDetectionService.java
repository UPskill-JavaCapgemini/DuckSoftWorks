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
    private Task task;

    @Setter
    TaskRepository taskRepository;

    @Override
    public String call() throws Exception {
        ILanguageDetector lang = new LanguageAnalyzer();
        String language = lang.analyze(task.getInputUrl().getUrl());
        Optional<Task> currentTask = taskRepository.findById(task.getId());

        if (currentTask.isPresent() && currentTask.get().isStatusProcessing()) {
            currentTask.get().updateLanguage(language);
            taskRepository.saveTask(currentTask.get());
        }

        return language;
    }
}
