package LanguageDetection.Domain;

import LanguageDetection.domain.ValueObjects.Language;
import LanguageDetection.domain.ValueObjects.Text;
import LanguageDetection.domain.entities.example.Task;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class ApplicationTest {

    @Test
    public void shouldCreateATaskWithCorrectAttributes() {
        //Arrange
        String text = "Olá é extraordinário como chegaste até aqui";
        Language lg = Language.PORTUGUESE;

        Task task = new Task( text , lg);
        assertNotEquals(task.getDate(),new Date(System.currentTimeMillis()) );
        assertEquals(task.getLang(), Language.valueOf("PORTUGUESE"));
        assertEquals(task.getText(), new Text(text));
    }

}