package LanguageDetection.domain.ValueObjects;


import LanguageDetection.domain.shared.ValueObject;
import LanguageDetection.domain.util.BusinessValidation;

public class URL implements ValueObject {

    private final String url;


    public URL(String url){
        BusinessValidation.nonEmpty(url,"Text for analises should neither be null nor empty");
        this.url=url;
    }

    @Override
    public String toString() {
        return url;
    }

    /* For ORM purposes */
    public URL(){
        this.url=null;
    }

    public String getUrl(){
        return this.url;
    }

}