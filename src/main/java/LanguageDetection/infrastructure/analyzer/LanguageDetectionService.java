package LanguageDetection.infrastructure.analyzer;

import LanguageDetection.domain.DomainService.ILanguageDetector;
import LanguageDetection.domain.model.ValueObjects.Language;
import LanguageDetection.domain.model.ValueObjects.TaskResult;
import LanguageDetection.domain.model.Task;
import LanguageDetection.infrastructure.repositories.TaskRepository;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class LanguageDetectionService implements ILanguageDetector{



    @Autowired
    AnalysisHelper analyzerService;



    /**
     * Responsible for instantiate a new Thread for asynchronous language analysis.
     *
     * @param task instance of object already created
     */
    public void languageAnalysis(Task task) {

        analyzerService.setTaskToBeAnalyzed(task);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(analyzerService);

        executorService.shutdown();
    }

}

@Slf4j
@Component
class AnalysisHelper implements Runnable {
    @Setter
    private Task taskToBeAnalyzed;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    LanguageAnalyzer lang;

    @SneakyThrows
    @Override
    //TODO check if passing a TaskResult is appropriated ??
    public void run() {
        //retirar isto
        Language analyzedLanguage = lang.analyze(taskToBeAnalyzed);
        Optional<Task> currentTask = taskRepository.findByTaskIdAndUserId(taskToBeAnalyzed.getId(), taskToBeAnalyzed.getUserId());

        if (currentTask.isPresent()) {
            TaskResult updatedTaskResult = new TaskResult(analyzedLanguage);
            if(currentTask.get().concludeTask(updatedTaskResult)){
                taskRepository.saveTask(currentTask.get());
                log.info("Language: " + analyzedLanguage);
            }
        }
    }
}
