package LanguageDetection.domain.factories;

import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.entities.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskFactory implements ITaskFactory {


    @Override
    public Task createTask(InputUrl url, Task.Category category, TimeOut timeOut) {
        return null;
    }
}
