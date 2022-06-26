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
/**
 * Represents the TaskService.The domain service containing the validation logic for Task related functionalities
 */
public class TaskService {

    private static final long CONSTANT_TO_MINUTES = 60000L;

    @Autowired
    ITaskRepository iTaskRepository;

    @Autowired
    ILanguageDetector iLanguageDetector;

    @Autowired
    TaskFactory taskFactory;

    /**
     * This method attempts to  create and save a Task
     * It will create and save the Task if the url , timeout and category are valid. Otherwise, no Task will be created and saved.
     *
     * @param url the String with the url to be persisted in the database and used for language analysis
     * @param timeout the int to be persisted in the database used for interrupting a language analysis
     * @param category the String with the category to be persisted in the database and used for categorizing the task
     * @throws MalformedURLException if the provided url is does not have an HTTP protocol or if parsing the url was unsuccessful
     * @return a Task wrapped in an optional if Task creation and save was successful
     * An empty Optional if not
     */
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

    /**
     * This method fetches information for all Tasks persisted in the database and returns a list containing them if there are any
     * or an empty list if no Tasks were persisted in the database
     *
     * @return a list of Tasks for the user, if tasks were found. An empty list if not.
     */
    public List<Task> findAllTasks() {
        return iTaskRepository.findAllTasks(getUserNameId());
    }


    /**
     * This method fetches information for all Tasks persisted in the database by TaskStatus and returns a list containing them if there are any
     * or an empty list if no Tasks were persisted in the database
     *
     * @param status The TaskStatus to be used for Task search
     * @return a list of Tasks for the user, if tasks were found. An empty list if not.
     */
    public List<Task> findByStatusContaining(TaskStatus status) {
        return iTaskRepository.findByStatusContaining(status, getUserNameId());
    }

    /**
     * This method fetches information for all Tasks persisted in the database by Category and returns a list containing them if there are any
     * or an empty list if no Tasks were persisted in the database
     *
     * @param category The Category to be used for Task search
     * @return a list of Tasks for the user, if tasks were found. An empty list if not.
     */
    public List<Task> findByCategoryNameContaining(Category category) {
        return iTaskRepository.findByCategoryNameContaining(category, getUserNameId());
    }

    /**
     * This method fetches information for all Tasks persisted in the database by TaskStatus and Category and returns a list containing them if there are any
     * or an empty list if no Tasks were persisted in the database
     *
     * @param status The TaskStatus to be used for Task search
     * @param category The Category to be used for Task search
     * @return a list of Tasks for the user, if tasks were found. An empty list if not.
     */
    public List<Task> findByStatusAndByCategoryContaining(TaskStatus status, Category category) {
        return iTaskRepository.findByStatusAndByCategoryContaining(status, category, getUserNameId());
    }

    /**
     * This method attempts to cancel an ongoing language analysis process for a user specific Task, if this task is processing
     *
     * @param id the Long containing the Task id which is to be cancelled
     * @return A cancelled Task wrapped in an Optional if cancelling the Task was successful
     * An empty Optional if not
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

    /**
     * This method initializes the language analysis process asynchronously
     * It also starts a timer for interrupting language analysis by using the provided Task TimeOut
     *
     * @param savedTask The Task previously created and persisted that is to be analysed and attributed with a TaskResult
     */

    private void initializeLanguageAnalysis(Task savedTask) {
        iLanguageDetector.languageAnalysis(savedTask);
        initializeTimer(savedTask);
    }

    /**
     * This method nitializes a new timer to handle timeout for language analysis which was sent from user input
     *It schedules a TimeOut TimerTask to interrupt the language analysis based on Task TimeOut
     *
     * @param taskrepo The Task submitted for language analysis to be interrupted by TimeOut
     * @return The Timer containing the information about the task to be interrupted by TimeOut
     */
    private Timer initializeTimer(Task taskrepo) {
        Timer timer = new Timer(Thread.currentThread().getName(), true);
        TimerTask interruptReturnedValues = timeOutAnalysis(taskrepo);
        timer.schedule(interruptReturnedValues, (taskrepo.getTimeOut().getTimeOut() * CONSTANT_TO_MINUTES));
        return timer;
    }

    /**
     * This method creates a TimerTask to run as scheduled by the Timer and interrupt analysis defined by the Task TimeOut
     *
     * @param task The task meant to be interrupted automatically after a given TimeOut
     * @return The TimerTask that was successful, or not, in cancelling a Task language analysis after a TimeOut
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

    /**
     * This method verifies if a url has been persisted and is currently being processed for language analysis
     *
     * @param url the url to be verified if it is being processed for language analysis
     * @return true if it is persisted and processing, false if not
     */
    private boolean isBeingAnalyzed(String url) throws MalformedURLException {
        InputUrl inputUrl = new InputUrl(url);
        return iTaskRepository.existsByUrlAndIsProcessing(inputUrl, getUserNameId());
    }
}
