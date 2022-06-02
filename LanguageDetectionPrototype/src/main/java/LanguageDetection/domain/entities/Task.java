package LanguageDetection.domain.entities;

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
 * This Class represents the tasks created to detect predominant language of text present in the given URL
 *  * @author DuckSoftWorks
 */

@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Task implements AggregateRoot<Date> {


    /**
     * The date of the task
     */
    @Getter
    Date date;
    /**
     * The text of the task
     */
    @Getter
    URL url;
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
     * The category defined by the user for the task
     */
    @Getter
    Category category;

    /**
     * Constructor of the task that buidls the task receiving the text and the language detected
     *
     * @param url URL of the text to be analyzed
     * @param timeOut time limit to conclude the task
     * @param category chosen by the client for a type of text
     */

    public Task(URL url, TimeOut timeOut, Category category) {
        // should id be here too??
        this.date = new Date(System.currentTimeMillis());
        this.url = url;
        this.lang = Language.DETECTING;
        this.currentStatus = CurrentStatus.Processing;
        this.timeOut = timeOut;
        this.category = category;
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
        SPANISH,
        DETECTING
    }

    public enum CurrentStatus {
        Concluded,
        Canceled,
        Processing
    }

    public enum Category {
        Economics,
        Philosophy,
        Mechanics,
        Nutrition,
        Sport
    }
}
