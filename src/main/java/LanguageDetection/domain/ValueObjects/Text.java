package LanguageDetection.domain.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;

import java.util.Locale;

import static LanguageDetection.domain.util.BusinessValidation.isOnlyNumbers;
import static LanguageDetection.domain.util.BusinessValidation.isOnlySpecialCharacters;

public class Text implements ValueObject {

    private String text;


    public Text(String text) {
        String cleanedText = cleanUpInputText(text);
        if(isOnlyNumbers(cleanedText) || isOnlySpecialCharacters(cleanedText) || cleanedText.isEmpty() || cleanedText.isBlank()) {
            throw new IllegalArgumentException("The text is not valid");
        } else {
            this.text = cleanedText;
        }
    }

    /**
     * Cleans up the string received via input.
     * Strips the string of multiple whitespaces through the use of a regex.
     *
     * @param text the string that is cleaned up with the regex.
     * @return the cleaned up text.
     */
    protected String cleanUpInputText(String text) {
        return text.trim().toLowerCase(Locale.ROOT)
                .replaceAll("[^a-zA-Z\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u024F]", " ")
                .replaceAll("\\s+", " "); // MULTIPLE_WHITESPACE
    }


    public String getTextContent() {
        return text;
    }

}
