package LanguageDetection.infrastructure.datamodel.assemblers;


import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.entities.Task;
import LanguageDetection.infrastructure.datamodel.TaskJpa;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class TaskDomainDataAssembler {

    public TaskJpa toData(Task task) {
        return new TaskJpa(task.getUrl().getUrl(), task.getTimeOut(), task.getCategory());
    }

    public Task toDomain(TaskJpa taskJpa) throws MalformedURLException {
        URL input = new URL(taskJpa.getUrl());
        return new Task(new InputUrl(input), taskJpa.getTimeOut(), taskJpa.getCategory());
    }

}