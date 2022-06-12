package LanguageDetection.domain.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;
import LanguageDetection.domain.util.BusinessValidation;

import javax.persistence.Embeddable;
import javax.persistence.Table;


@Embeddable
@Table
public class TimeOut implements ValueObject {
    private final int timeOut;

    public TimeOut(int timeOut) {
        BusinessValidation.inRange(timeOut,1,5,"This TimeOut is not in the proper range");
        this.timeOut = timeOut;
    }

    /* For ORM purposes */
    public TimeOut(){ this.timeOut = 0;}

    @Override
    public String toString() {
        return String.valueOf(this.timeOut);
    }

    public int getTimeOut(){
        return this.timeOut;
    }



}
