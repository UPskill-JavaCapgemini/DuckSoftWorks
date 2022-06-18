package LanguageDetection.domain.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;

public class Text implements ValueObject {

    private String text;


    public Text(String url) {
        //TODO: Add validation here.
        this.text = url;
    }
}
