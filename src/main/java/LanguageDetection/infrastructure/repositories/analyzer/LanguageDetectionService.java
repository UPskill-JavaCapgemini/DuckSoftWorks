package LanguageDetection.infrastructure.repositories.analyzer;

import LanguageDetection.domain.DomainService.ILanguageDetector;
import LanguageDetection.domain.model.ValueObjects.Language;
import LanguageDetection.domain.model.ValueObjects.TaskResult;
import LanguageDetection.domain.model.Task;
import LanguageDetection.infrastructure.repositories.TaskRepository;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class LanguageDetectionService implements ILanguageDetector{

    private static final long CONSTANT_TO_MINUTES = 60000L;


    @Autowired
    TaskRepository taskRepo;

    @Autowired
    AsyncClass analyzerService;



    /**
     * Responsible for instantiate a new Thread for asynchronous language analysis.
     *
     * @param task instance of object already created
     */
    public void languageAnalysis(Task task) {

        analyzerService.setTaskToBeAnalyzed(task);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(analyzerService);

        initializeTimer(task);

        executorService.shutdown();
    }

    /**
     * Initializes a new timer to handle timeout for language analysis which was sent from user input.
     *
     * @param taskrepo Task instance which was already created and has timeout limit.
     * @return timer
     */
    protected Timer initializeTimer(Task taskrepo) {
        Timer timer = new Timer(Thread.currentThread().getName(), true);
        TimerTask interruptReturnedValues = timeOutAnalysis(taskrepo);
        timer.schedule(interruptReturnedValues, (taskrepo.getTimeOut().getTimeOut() * CONSTANT_TO_MINUTES));
        return timer;
    }

    /**
     * Handles the canceling method after timelimit reaches.
     *
     * @param task task instance of what needs to be canceled.
     * @return Timer task
     */
    protected TimerTask timeOutAnalysis(Task task) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    task.updateStatus(Task.TaskStatus.Canceled);
                    taskRepo.saveTask(task);
                } catch (ObjectOptimisticLockingFailureException | StaleObjectStateException e) {
                    log.warn("Unsuccessful save: " + e.getMessage());
                }
            }
        };
        return timerTask;
    }


}

@Slf4j
@Component
class AsyncClass implements Runnable {
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
        Optional<Task> currentTask = taskRepository.findByTaskId(taskToBeAnalyzed.getId());

        if (currentTask.isPresent()) {
            TaskResult updatedTaskResult = new TaskResult(analyzedLanguage);
            if(currentTask.get().concludeTask(updatedTaskResult)){
                taskRepository.saveTask(currentTask.get());
            }
        }
    }
}
