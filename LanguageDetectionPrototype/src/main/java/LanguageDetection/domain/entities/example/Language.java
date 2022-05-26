package LanguageDetection.domain.entities.example;

public interface Language {
    boolean sameAs(Object other);

    Long identity();
}
