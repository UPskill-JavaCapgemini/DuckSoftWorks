package LanguageDetection.domain.model.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;
import LanguageDetection.domain.util.BusinessValidation;

import javax.persistence.Embeddable;
import javax.persistence.Table;


@Embeddable
public class TimeOut implements ValueObject {
    private final int timeOut;

    public TimeOut(int timeOut) {
        BusinessValidation.inRange(timeOut,1,5,"This TimeOut is not in the proper range");
        this.timeOut = timeOut;
    }

    /* For ORM purposes */
    protected TimeOut(){ this.timeOut = 0;}

    public int getTimeOut(){
        return this.timeOut;
    }



}
