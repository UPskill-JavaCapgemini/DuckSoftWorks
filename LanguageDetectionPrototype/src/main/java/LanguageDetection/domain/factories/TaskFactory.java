package LanguageDetection.domain.factories;

import com.example.domain.entities.example.Example;
import org.springframework.stereotype.Service;

@Service
public class TaskFactory implements ITaskFactory {

    // todo: is it really needed?
    @Override
    public Example createExample(String name) {
        return new Example(name);
    }

    @Override
    public Example createExample(Long id, String name) {
        return new Example(id, name);
    }
}
