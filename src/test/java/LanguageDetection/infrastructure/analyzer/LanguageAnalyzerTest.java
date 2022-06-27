package LanguageDetection.infrastructure.analyzer;

import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.Task;
import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.model.ValueObjects.Language;
import LanguageDetection.domain.model.ValueObjects.TimeOut;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class LanguageAnalyzerTest {

    @InjectMocks
    LanguageAnalyzer languageAnalyzer;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void analyzeShouldReturnEnglishWhenTextIsInEnglish() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ParseException {
        //arrange
        String url = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        int inputTimeOut = 1;
        String inputCategory = "Sports";
        Category category = new Category(inputCategory);
        TimeOut timeOut = new TimeOut(inputTimeOut);
        InputUrl inputUrl = new InputUrl(url);

        Constructor<Task> taskConstructor = Task.class.getDeclaredConstructor(InputUrl.class,TimeOut.class,Category.class,Long.class);
        taskConstructor.setAccessible(true);
        Task task = taskConstructor.newInstance(inputUrl,timeOut,category,1L);

        Language language = languageAnalyzer.analyze(task);

        Assertions.assertEquals(language, Language.ENGLISH);
    }

    @Test
    void analyzeShouldReturnPortugueseWhenTextIsInPortuguese() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ParseException {
        //arrange
        String url = "http://users.isr.ist.utl.pt/~cfb/VdS/v302.txt";
        int inputTimeOut = 1;
        String inputCategory = "Sports";
        Category category = new Category(inputCategory);
        TimeOut timeOut = new TimeOut(inputTimeOut);
        InputUrl inputUrl = new InputUrl(url);

        Constructor<Task> taskConstructor = Task.class.getDeclaredConstructor(InputUrl.class,TimeOut.class,Category.class,Long.class);
        taskConstructor.setAccessible(true);
        Task task = taskConstructor.newInstance(inputUrl,timeOut,category,1L);

        Language language = languageAnalyzer.analyze(task);

        Assertions.assertEquals(language, Language.PORTUGUESE);
    }

    @Test
    void analyzeShouldReturnSpanishWhenTextIsInSpanish() throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ParseException {
        //arrange
        String url = "https://firstneighbor.com/transcribed-audio/Mejor-Llamada-de-Caso-en-Espanol.txt";
        int inputTimeOut = 1;
        String inputCategory = "Sports";
        Category category = new Category(inputCategory);
        TimeOut timeOut = new TimeOut(inputTimeOut);
        InputUrl inputUrl = new InputUrl(url);

        Constructor<Task> taskConstructor = Task.class.getDeclaredConstructor(InputUrl.class,TimeOut.class,Category.class,Long.class);
        taskConstructor.setAccessible(true);
        Task task = taskConstructor.newInstance(inputUrl,timeOut,category,1L);

        Language language = languageAnalyzer.analyze(task);

        Assertions.assertEquals(language, Language.SPANISH);
    }

}