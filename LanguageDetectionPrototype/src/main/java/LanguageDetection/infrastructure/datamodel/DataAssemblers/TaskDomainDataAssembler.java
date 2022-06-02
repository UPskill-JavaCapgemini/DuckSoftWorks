package LanguageDetection.infrastructure.datamodel.DataAssemblers;

import LanguageDetection.domain.entities.Task;
import LanguageDetection.infrastructure.datamodel.TaskJpa;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

@Service
public class TaskDomainDataAssembler {

    public TaskJpa toData(Task task) {
        return new TaskJpa(task.getUrl().getUrl(), task.getTimeOut().getTimeOut(), task.getCategory().toString(), task.getLanguage(), task.getCurrentStatus());
    }

    public Task toDomain(TaskJpa taskJpa) throws MalformedURLException {
        return new Task(taskJpa.getUrl(), taskJpa.getTimeOut(), taskJpa.getCategory());
    }

}