package LanguageDetection.Domain;

import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.Task;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskTest {
/*
    @Test
    public void shouldCreateATaskWithCorrectAttributes() {
        //Arrange
        String text = "Olá é extraordinário como chegaste até aqui";
        Task.Language lg = Task.Language.PORTUGUESE;

        Task task = new Task( text , lg);

        //Assert
        assertNotEquals(task.getDate(),new Date(System.currentTimeMillis()) );
        assertEquals(task.getLang(), Task.Language.valueOf("PORTUGUESE"));
        assertEquals(task.getText().toString(), "Olá é extraordinário como chegaste até aqui");
    }

*/

    @Test
    public void shouldCreateATaskWhenUrlWithTxtIsProvided() throws MalformedURLException {
        //Arrange
        InputUrl inputURlWithTxt = new InputUrl("http://www.textexample.com/text/text.txt");
        TimeOut inputTimeout = new TimeOut(3);
        Category inputCategory = new Category("mechanics");
        Task testableTask;

        //Act
        testableTask = new Task(inputURlWithTxt.getUrl(), inputTimeout.getTimeOut(), inputCategory);

        //Assert
        assertNotNull(testableTask);
    }

    @Test
    public void shouldThrowExceptionWhenUrlHasNoTxt() throws MalformedURLException {
        //Arrange
        TimeOut inputTimeout = new TimeOut(3);
        Category inputCategory = new Category("mechanics");

        //Act

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            Task testableTask = new Task("http://www.notxturl.com",inputTimeout.getTimeOut(),inputCategory);
        });


        //Assert
        assertEquals(illegalArgumentException.getMessage(),"The URL doesn't contain a txt file");
    }
}

