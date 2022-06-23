package LanguageDetection.domain.model.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;

import java.util.Locale;

import static LanguageDetection.domain.util.BusinessValidation.isOnlyNumbers;
import static LanguageDetection.domain.util.BusinessValidation.isOnlySpecialCharacters;

public class Text implements ValueObject {

    private final int MAX_STRING_LENGTH = 5000000;

    private final String text;


    public Text(String text) {
        String verifiedText = verifyTextSize(text);
        String cleanedText = cleanUpInputText(verifiedText);
        if (cleanedText.isEmpty() || cleanedText.isBlank()) {
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
    private String cleanUpInputText(String text) {
        return text.trim().toLowerCase(Locale.ROOT)
                .replaceAll("[^a-zA-Z\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u024F]", " ")
                .replaceAll("\\s+", " "); // MULTIPLE_WHITESPACE
    }

    private String verifyTextSize(String text) {
        if (text.length() >= MAX_STRING_LENGTH) {
            return text.substring(0, MAX_STRING_LENGTH);
        } else {
            return text;
        }
    }


    public String getTextContent() {
        return text;
    }

}
