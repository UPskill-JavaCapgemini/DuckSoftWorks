package LanguageDetection.domain.ValueObjects;


import LanguageDetection.domain.shared.ValueObject;
import LanguageDetection.domain.util.BusinessValidation;


public class Text implements ValueObject {
    private final String text;

    public Text(String txt){
        BusinessValidation.nonEmpty(txt,"Text for analises should neither be null nor empty");
        this.text=txt;
    }

    /* For ORM purposes */
    public Text(){
        this.text=null;
    }

    public String getText(){
        return this.text;
    }

}