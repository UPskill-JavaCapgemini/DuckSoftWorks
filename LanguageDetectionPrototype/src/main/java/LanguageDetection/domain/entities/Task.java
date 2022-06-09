package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.shared.AggregateRoot;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Repository;


import javax.persistence.*;
import java.net.MalformedURLException;
import java.util.Date;

/**
 * This Class represents the tasks created to detect predominant language of text present in the given URL
 *  * @author DuckSoftWorks
 */

@EqualsAndHashCode
@NoArgsConstructor
@Repository
@Entity
@Table
public class Task implements AggregateRoot<Long> {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    /**
     * The date of the task
     */
    @Getter
    @CreationTimestamp
    @Column(name="timestamp", nullable = false, updatable = false)
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
    @Enumerated(EnumType.STRING)
    @Getter
    Language language;
    /**
     * The actual state of the task
     */
    @Enumerated(EnumType.STRING)
    @Getter
    CurrentStatus currentStatus;
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

    /**
     * Task's constructor that receives the text and the language detected
     *
     * @param inputUrl URL of the text to be analyzed
     * @param timeOut time limit to conclude the task
     * @param category chosen by the client for a type of text
     */

    public Task(String inputUrl, int timeOut, Category category) throws MalformedURLException {
        this.id = null;
        this.date = null;
        this.inputUrl = new InputUrl(inputUrl);
        this.language = null;
        this.currentStatus = CurrentStatus.Processing;
        this.timeOut = new TimeOut(timeOut);
        this.category = category;
    }

    public Task(Task task,String language) throws MalformedURLException {
        this.id = task.identity();
        this.date = task.getDate();
        this.inputUrl = task.getInputUrl();
        this.language = Task.Language.valueOf(language);
        this.currentStatus = CurrentStatus.Concluded;
        this.timeOut = task.getTimeOut();
        this.category = task.getCategory();
    }

    public Task(Task task) throws MalformedURLException {
        this.id = task.identity();
        this.date = task.getDate();
        this.inputUrl = task.getInputUrl();
        this.language = null;
        this.currentStatus = CurrentStatus.Canceled;
        this.timeOut = task.getTimeOut();
        this.category = task.getCategory();
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

    public enum Language {
        ENGLISH,
        PORTUGUESE,
        SPANISH
    }

    @Getter
    public enum CurrentStatus {
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
                ", language=" + language +
                ", currentStatus=" + currentStatus +
                ", timeOut=" + timeOut +
                ", category=" + category;
    }
}
