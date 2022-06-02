package LanguageDetection.infrastructure.datamodel.assemblers;


import LanguageDetection.domain.entities.Task;
import LanguageDetection.infrastructure.datamodel.TaskJpa;
import org.springframework.stereotype.Service;

@Service
public class TaskDomainDataAssembler {

    public TaskJpa toData(Task task) {
        return new TaskJpa(task.getUrl(), task.getTimeOut(), task.getCategory());
    }

    public Task toDomain(TaskJpa taskJpa) {
        return new Task(taskJpa.getUrl(), taskJpa.getTimeOut(), taskJpa.getCategory());
    }

}