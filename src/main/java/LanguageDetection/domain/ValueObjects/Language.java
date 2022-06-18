package LanguageDetection.domain.ValueObjects;


import LanguageDetection.domain.shared.ValueObject;

/**
 * Possibilities of what can be the Task language
 */

public enum Language implements ValueObject {
    ENGLISH,
    PORTUGUESE,
    SPANISH,
}