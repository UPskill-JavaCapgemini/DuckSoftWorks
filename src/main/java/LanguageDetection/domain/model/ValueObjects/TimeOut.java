package LanguageDetection.domain.model.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;
import LanguageDetection.domain.util.BusinessValidation;

import javax.persistence.Embeddable;
import javax.persistence.Table;


@Embeddable
/**
 * Represents the TimeOut of a Task. The user provided timeout, in minutes, for interrupting a task language analysis
 * It validates if the provided timeout is between the preset range of acceptable values , 1 and 5 minutes
 */
public class TimeOut implements ValueObject {
    private final int timeOut;

    public TimeOut(int timeOut) {
        BusinessValidation.inRange(timeOut,1,5,"This TimeOut is not in the proper range");
        this.timeOut = timeOut;
    }

    /* For ORM purposes */
    protected TimeOut(){ this.timeOut = 0;}

    /**
     * This method returns the TimeOut, in minutes
     *
     * @return the Timeout, in minutes
     */
    public int getTimeOut(){
        return this.timeOut;
    }



}
