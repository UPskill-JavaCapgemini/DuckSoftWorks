package LanguageDetection.domain.entities.example;

import LanguageDetection.domain.ValueObjects.Text;
import LanguageDetection.domain.shared.AggregateRoot;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
     * The date of the task
     */
    @Getter
    Date date;
    /**
     * The text of the task
     */
    @Getter
    Text text;
    /**
     * The language detected on the text
     */
    @Getter
    Language lang;

    /**
     * Constructor of the task that buidls the task receiving the text and the language detected
     * @param txt  inserted text for be analyzed
     * @param lg language that was detected in the text
     */
    public Task (String txt, Language lg){
        this.date = new Date(System.currentTimeMillis());
        this.text = new Text(txt);
        this.lang = lg;
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

}
