package LanguageDetection.domain.factories;

import LanguageDetection.domain.entities.example.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskFactory implements ITaskFactory {

    // todo: is it really needed?
    @Override
    public Task createExample(String name) {
        return new Task();
    }

    @Override
    public Task createExample(Long id, String name) {
        return new Task();
    }
}
