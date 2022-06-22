package LanguageDetection.domain.DomainService;

import LanguageDetection.domain.model.ITaskRepository;
import LanguageDetection.domain.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Component
public class TaskService {

    private static final long CONSTANT_TO_MINUTES = 60000L;

    @Autowired
    ITaskRepository iTaskRepository;

    @Autowired
    ILanguageDetector iLanguageDetector;



    public void initializeLanguageAnalysis(Task savedTask){
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
                    if(task.cancelTask()){
                        iTaskRepository.saveTask(task);
                    }
                } catch (ObjectOptimisticLockingFailureException | StaleObjectStateException e) {
                    log.warn("Unsuccessful save: " + e.getMessage());
                }
            }
        };
        return timerTask;
    }
}
