package LanguageDetection.domain.DomainService;


import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.ITaskRepository;
import LanguageDetection.domain.model.Task;
import LanguageDetection.domain.model.TaskFactory;
import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.model.ValueObjects.TaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import static LanguageDetection.domain.DomainService.UserDetailsDomainService.getUserNameId;


@Slf4j
@Component
public class TaskService {

    private static final long CONSTANT_TO_MINUTES = 60000L;

    @Autowired
    ITaskRepository iTaskRepository;

    @Autowired
    ILanguageDetector iLanguageDetector;

    @Autowired
    TaskFactory taskFactory;


    public Optional<Task> createAndSaveTask(String url, int timeout, String category) throws MalformedURLException {

        if (isBeingAnalyzed(url))
            return Optional.empty();

        Optional<Task> opCreatedTask = taskFactory.createTask(url, timeout, category);
        if (opCreatedTask.isPresent()) {
            Task savedTask = this.iTaskRepository.saveTask(opCreatedTask.get());
            initializeLanguageAnalysis(savedTask);
            return Optional.of(savedTask);
        }
        return Optional.empty();
    }


    public List<Task> findAllTasks() {
        return iTaskRepository.findAllTasks(getUserNameId());
    }

    public List<Task> findByStatusContaining(TaskStatus status) {
        return iTaskRepository.findByStatusContaining(status, getUserNameId());
    }

    public List<Task> findByCategoryNameContaining(Category category) {
        return iTaskRepository.findByCategoryNameContaining(category, getUserNameId());
    }

    public List<Task> findByStatusAndByCategoryContaining(TaskStatus status, Category category) {
        return iTaskRepository.findByStatusAndByCategoryContaining(status, category, getUserNameId());
    }

    /**
     * Handles the cancellation process of a language analysis from user input
     *
     * @param id if of a task that user wants to cancel
     * @return TaskDTO instance with all information if a task is canceled or empty if that id does not correspond to one task
     */
    public Optional<Task> cancelTaskAnalysis(Long id) {
        Optional<Task> optionalTask = iTaskRepository.findByTaskId(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            if (task.cancelTask()) {
                iTaskRepository.saveTask(task);
                return Optional.of(task);
            }
        }
        return Optional.empty();
    }


    private void initializeLanguageAnalysis(Task savedTask) {
        iLanguageDetector.languageAnalysis(savedTask);
        initializeTimer(savedTask);
    }

    /**
     * Initializes a new timer to handle timeout for language analysis which was sent from user input.
     *
     * @param taskrepo Task instance which was already created and has timeout limit.
     * @return timer
     */
    private Timer initializeTimer(Task taskrepo) {
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
    private TimerTask timeOutAnalysis(Task task) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    if (task.cancelTask()) {
                        iTaskRepository.saveTask(task);
                    }
                } catch (ObjectOptimisticLockingFailureException | StaleObjectStateException e) {
                    log.warn("Unsuccessful save: " + e.getMessage());
                }
            }
        };
        return timerTask;
    }


    private boolean isBeingAnalyzed(String url) throws MalformedURLException {
        InputUrl inputUrl = new InputUrl(url);
        return iTaskRepository.existsByUrlAndIsProcessing(inputUrl, getUserNameId());
    }
}
