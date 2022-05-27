package LanguageDetection.domain.factories;

import LanguageDetection.domain.ValueObjects.Language;
import LanguageDetection.domain.entities.example.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskFactory implements ITaskFactory {



    @Override
    public Task createTask(String string, Language language) {

        return new Task(string, language);
    }
}
