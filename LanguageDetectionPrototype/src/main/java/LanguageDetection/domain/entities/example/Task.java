package LanguageDetection.domain.entities.example;

import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.ValueObjects.URL;
import LanguageDetection.domain.shared.AggregateRoot;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * This Class represents the tasks created to detect predominant language of the inserted text
 *  * @author DuckSoftWorks
 */

@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Task implements AggregateRoot<Date> {

    /**
     * The autogenerate id of the task
     */
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskId;
    /**
     * The date of the task
     */
    @Getter
    Date date;
    /**
     * The text of the task
     */
    @Getter
    URL text;
    /**
     * The language detected on the text
     */
    @Getter
    Language lang;
    /**
     * The actual state of the task
     */
    @Getter
    CurrentStatus currentStatus;
    /**
     * The time task has to be concluded before it is automatically canceled
     */
    @Getter
    TimeOut timeOut;

    /**
     * Constructor of the task that buidls the task receiving the text and the language detected
     *
     * @param date inserted text for be analyzed
     * @param   language that was detected in the text
     */

    public Task(Date date, URL text, Language lang, TimeOut timeOut) {
        // should id be here too??
        this.date = new Date(System.currentTimeMillis());
        this.text = text;
        this.lang = lang;
        this.currentStatus = CurrentStatus.Processing;
        this.timeOut = timeOut;
    }




    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(Date other) {
        return AggregateRoot.super.compareTo(other);
    }

    /**
     * method that identify the task
     *
     * @return the date of the task
     */
    @Override
    public Date identity() {
        return this.date;
    }

    @Override
    public boolean hasIdentity(Date id) {
        return AggregateRoot.super.hasIdentity(id);
    }

    public enum Language {
        ENGLISH,
        PORTUGUESE,
        SPANISH
    }

    public enum CurrentStatus {
        Concluded,
        Canceled,
        Processing
    }
}
