package LanguageDetection.domain.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;
import lombok.Getter;

public class Text implements ValueObject {
    @Getter
    private final String text;

    public Text(String text) {
        if (text == null || text.isEmpty())
            throw new IllegalArgumentException("The text must have at least one character!");
        this.text = text;
    }

}
