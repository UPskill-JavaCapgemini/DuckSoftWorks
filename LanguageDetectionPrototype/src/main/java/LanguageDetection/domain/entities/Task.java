package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.shared.AggregateRoot;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * This Class represents the tasks created to detect predominant language of text present in the given URL
 *  * @author DuckSoftWorks
 */

@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Task implements AggregateRoot<Date> {


    Long id;
    /**
     * The date of the task
     */
    @Getter
    Date date;
    /**
     * The text of the task
     */
    @Getter
    InputUrl url;
    /**
     * The language detected on the text
     */
    @Getter
    Language language;
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
     * Task's constructor that receives the text and the language detected
     *
     * @param url URL of the text to be analyzed
     * @param timeOut time limit to conclude the task
     * @param category chosen by the client for a type of text
     */

    public Task(String url, int timeOut, String category) throws MalformedURLException {
        this.id = null;
        this.date = null;
        this.url = new InputUrl(url);
        this.language = null;
        this.currentStatus = CurrentStatus.Processing;
        this.timeOut = new TimeOut(timeOut);
        this.category = new Category(category);
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
