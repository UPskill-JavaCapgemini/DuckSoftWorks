package LanguageDetection.domain.factories;

import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.ValueObjects.URL;
import LanguageDetection.domain.entities.Task;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TaskFactory implements ITaskFactory {



    @Override
    public Task createTask(Task.Category category, TimeOut timeOut, URL url) {

        return new Task(url, timeOut, category);
    }
}
