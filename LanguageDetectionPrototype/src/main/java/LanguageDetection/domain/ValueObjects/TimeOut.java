package LanguageDetection.domain.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;

public class TimeOut implements ValueObject {
    private final int timeOut;

    public TimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    /* For ORM purposes */
    public TimeOut(){
        this.timeOut = 0;
    }

    @Override
    public String toString() {
        return String.valueOf(this.timeOut);
    }

    public int getTimeOut(){
        return this.timeOut;
    }



}
