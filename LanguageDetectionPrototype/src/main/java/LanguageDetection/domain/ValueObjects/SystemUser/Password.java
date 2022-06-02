package LanguageDetection.domain.ValueObjects.SystemUser;

import LanguageDetection.domain.shared.ValueObject;

public class Password implements ValueObject {
    private String password;

    public Password (String pass){
        this.password = pass;
    }

    /* For ORM purposes */
    public Password(){
        this.password = null;
    }


}
