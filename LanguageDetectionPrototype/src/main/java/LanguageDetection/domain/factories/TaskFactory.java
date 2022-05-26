package LanguageDetection.domain.factories;

import LanguageDetection.domain.entities.example.Task;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TaskFactory implements ITaskFactory {



    @Override
    public Task createTask(String string) {
        return new Task(string);
    }
}
