package LanguageDetection.domain.factories;

import LanguageDetection.domain.entities.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskFactory implements ITaskFactory {



    @Override
    public Task createTask(String string, String language) {

        return new Task(string, Task.Language.valueOf(language));
    }
}
