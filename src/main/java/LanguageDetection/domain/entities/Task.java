package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.TaskResult;
import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.shared.AggregateRoot;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;


import javax.persistence.*;
import java.net.MalformedURLException;
import java.util.Date;

/**
 * This Class represents the tasks created to detect predominant language of text present in the given URL
 *
 * @author DuckSoftWorks
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
    @Column(name = "timestamp", nullable = false, updatable = false)
    Date date;

    /**
     * The text of the task
     */
    @Getter
    @Embedded
    InputUrl inputUrl;
    /**
     * The language detected on the text
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
    Category category;

    protected Task() {
    }

    /**
     * Constructor that receives all the information necessary to create a Task from user input
     *
     * @param inputUrl URL of the text to be analyzed
     * @param timeOut  time limit to conclude the task
     * @param category chosen by the client for a type of text
     * @throws MalformedURLException thrown if an url from input is invalid
     */

    public Task(String inputUrl, int timeOut, Category category) throws MalformedURLException {
        this.id = null;
        this.date = null;
        this.inputUrl = new InputUrl(inputUrl);
        this.taskResult = null;
        this.currentStatus = TaskStatus.Processing;
        this.timeOut = new TimeOut(timeOut);
        this.category = category;
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
     * method that identify the task
     *
     * @return the date of the task
     */
    @Override
    public Long identity() {
        return this.id;
    }

    @Override
    public boolean hasIdentity(Long id) {
        return AggregateRoot.super.hasIdentity(id);
    }

    public boolean isStatusProcessing() {
        return this.currentStatus == TaskStatus.Processing;
    }

    /**
     * Only to be used at asynchronous process, after language analysis
     *
     * @param updatedTaskResult
     */
    public void updateTaskResultLanguage(TaskResult updatedTaskResult) {
        this.taskResult = updatedTaskResult;
        this.updateStatus(TaskStatus.Concluded);
    }

    /**
     * Used to update Task status
     *
     * @param status new task status
     */
    public void updateStatus(TaskStatus status) {
        this.currentStatus = status;
    }

    /**
     * Possibilities of what can be the Task status
     */
    @Getter
    public enum TaskStatus {
        Concluded,
        Canceled,
        Processing
    }

    @Override
    public String toString() {
        return "Task: " +
                "id=" + id +
                ", date=" + date +
                ", url=" + inputUrl +
                ", language=" + taskResult +
                ", currentStatus=" + currentStatus +
                ", timeOut=" + timeOut +
                ", category=" + category;
    }


}
