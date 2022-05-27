package LanguageDetection.domain.ValueObjects;


import LanguageDetection.domain.shared.ValueObject;
import LanguageDetection.domain.util.BusinessValidation;


public class Text implements ValueObject {
    private final String text;

    protected Text(String txt){
        BusinessValidation.nonEmpty(txt,"Text for analises should neither be null nor empty");
        this.text=txt;
    }

    /* For ORM purposes */
    protected Text(){
        this.text=null;
    }

    protected String getText(){
        return this.text;
    }

}