package LanguageDetection.Domain.model.ValueObjects;

import LanguageDetection.domain.model.ValueObjects.Language;
import LanguageDetection.domain.model.ValueObjects.TaskResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskResultTest {

    @Test
    public void ensureTaskIsCreatedWithValidLanguageEnglish(){
        Language language = Language.valueOf("ENGLISH");

        //Act
        TaskResult taskResult = new TaskResult(language);

        assertEquals(taskResult.getLanguage(), Language.ENGLISH);
    }

    @Test
    public void ensureTaskIsCreatedWithValidLanguagePortuguese(){
        Language language = Language.valueOf("PORTUGUESE");

        //Act
        TaskResult taskResult = new TaskResult(language);

        assertEquals(taskResult.getLanguage(), Language.PORTUGUESE);
    }

    @Test
    public void ensureTaskIsCreatedWithValidLanguageSpanish(){
        Language language = Language.valueOf("SPANISH");

        //Act
        TaskResult taskResult = new TaskResult(language);

        assertEquals(taskResult.getLanguage(), Language.SPANISH);
    }

}