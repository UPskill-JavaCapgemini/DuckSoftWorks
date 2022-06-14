package LanguageDetection.application.services;

import LanguageDetection.domain.DomainService.ILanguageDetector;
import LanguageDetection.domain.DomainService.LanguageAnalyzer;
import LanguageDetection.domain.ValueObjects.Language;
import LanguageDetection.domain.ValueObjects.TaskResult;
import LanguageDetection.domain.entities.Task;
import LanguageDetection.infrastructure.repositories.TaskRepositoryRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


import java.util.Optional;
import java.util.concurrent.Callable;

@Async
@Slf4j
@Component
public class LanguageDetectionService implements Runnable{
    @Getter
    @Setter
        private Task taskToBeAnalyzed;

    @Setter
    TaskRepositoryRepository taskRepository;

    @SneakyThrows
    @Override
    //todo check if passing a TaskResult is appropriated
    public void run() {
        ILanguageDetector lang = new LanguageAnalyzer();
        Language analyzedLanguage = lang.analyze(taskToBeAnalyzed);
        Optional<Task> currentTask = taskRepository.findById(taskToBeAnalyzed.getId());

        if (currentTask.isPresent() && currentTask.get().isStatusProcessing()) {
            TaskResult updatedTaskResult = new TaskResult(analyzedLanguage);
            currentTask.get().updateTaskResultLanguage(updatedTaskResult);
            taskRepository.saveTask(currentTask.get());
        }
    }
}
