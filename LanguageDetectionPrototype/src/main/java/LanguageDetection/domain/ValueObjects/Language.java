package LanguageDetection.domain.ValueObjects;

public enum Language {

    ENGLISH("English"),
    PORTUGUESE("Portuguese"),
    SPANISH("Spanish");

    private final String LANGUAGE;

    Language(String language){
        this.LANGUAGE = language;
    }

}
