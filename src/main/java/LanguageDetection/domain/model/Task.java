package LanguageDetection.domain.model;

import LanguageDetection.domain.model.ValueObjects.TaskResult;
import LanguageDetection.domain.model.ValueObjects.TaskStatus;
import LanguageDetection.domain.model.ValueObjects.TimeOut;
import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.shared.AggregateRoot;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;


import javax.persistence.*;
import java.net.MalformedURLException;
import java.util.Date;

/**
 * Representes a task created to detect predominant language of text present in the given URL

 */

@EqualsAndHashCode
@Component
@Entity
@Table
public class Task implements AggregateRoot<Long> {

    /**
     * Task ID used to identify each Task
     */
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    /**
     * Version of id used on concurrent access to database
     */

    @Version
    Long version_id;

    /**
     * The date of the task
     */
    @Getter
    @CreationTimestamp
    @Column(name = "TimeOfCreation", nullable = false, updatable = false)
    Date date;

    /**
     * The text of the task
     */
    @Getter
    @Embedded
    InputUrl inputUrl;
    /**
     * The language analysis result of a task
     */
    @Getter
    @Embedded
    TaskResult taskResult;
    /**
     * The actual state of the task
     */
    @Enumerated(EnumType.STRING)
    @Getter

    TaskStatus currentStatus;

    /**
     * The time task has to be concluded before it is automatically canceled
     */
    @Getter
    @Embedded
    TimeOut timeOut;
    /**
     * The category defined by the user for the task
     */
    @Getter
    @ManyToOne
    @JoinColumn(name = "CategoryName")
    Category category;

    Long userId;

    /**
     * The empty Constructor for creating a task. Used for ORM purposes
     */
    protected Task() {
    }

    /**
     * The Constructor that receives all the information necessary to create a Task from user input
     *
     * @param inputUrl URL of the text to be analyzed
     * @param timeOut  time limit to conclude the task
     * @param category chosen by the client for a type of text
     * @throws MalformedURLException thrown if an url from input is invalid
     */

     protected Task(InputUrl inputUrl, TimeOut timeOut, Category category, Long userId) throws MalformedURLException {
        this.id = null;
        this.date = null;
        this.inputUrl = inputUrl;
        this.taskResult = null;
        this.currentStatus = TaskStatus.Processing;
        this.timeOut = timeOut;
        this.category = category;
        this.userId = userId;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(Long other) {
        return AggregateRoot.super.compareTo(other);
    }

    /**
     * This method returns the Task identity
     *
     * @return  a Long as the identity of Task
     */
    @Override
    public Long identity() {
        return this.id;
    }

    /**
     * This method verifies if the TaskStatus is Processing
     *
     * @return true if TaskStatus is Processing, false if not
     */

    private boolean isStatusProcessing() {
        return this.currentStatus == TaskStatus.Processing;
    }

    /**
     * This method verifies if the TaskStatus is Canceled
     *
     * @return true if TaskStatus is Canceled, false if not
     */
    private boolean isStatusCanceled() {
        return this.currentStatus == TaskStatus.Canceled;
    }

    /**
     * This method is meant to be used asynchronously to update the Task TaskStatus and TaskResult upon language analysis conclusion
     * It updates the Task Language result and TaskStatus
     *
     * @param updatedTaskResult The TaskResult with the analyzed Language
     * @return true if the Task language analysis has been concluded successfully, false if not
     */
    public boolean concludeTask(TaskResult updatedTaskResult) {
        if(!isStatusCanceled()){
            this.taskResult = updatedTaskResult;
            this.updateStatus(TaskStatus.Concluded);
            return true;
        }
        return false;
    }

    /**
     * This method attempts to update the Task TaskStatus to Canceled
     * If successful , updates the task with a Canceled TaskStatus
     *
     * @return true if the Task TaskStatus is updated to Canceled, false if not
     */
    public boolean cancelTask(){
        if(isStatusProcessing()){
            this.updateStatus(TaskStatus.Canceled);
            return true;
        }
        return false;
    }

    /**
     * This method updates that Task TaskStatus
     *
     * @param status The TaskStatus containing the information meant to update the Task
     */
    private void updateStatus(TaskStatus status) {
        this.currentStatus = status;
    }

}
