package LanguageDetection.domain.factories;

import LanguageDetection.domain.entities.Task;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

@Service
public class TaskFactory implements ITaskFactory {


    @Override
    public Task createTask(String url, int timeOut, String category) throws MalformedURLException {
        return new Task(url, timeOut, category);
    }
}
