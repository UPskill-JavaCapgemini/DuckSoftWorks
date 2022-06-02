package LanguageDetection.infrastructure.datamodel.assemblers;

import LanguageDetection.domain.entities.Task;
import LanguageDetection.infrastructure.datamodel.TaskJpa;

public class TaskAssembler {

    public TaskJpa toData (Task task) {
        return new TaskJpa(task.getDate(), task.getCategory(), task.getLang(), task.getTimeOut(), task.getUrl(), task.getCurrentStatus());
    }

    public TaskJpa toDomain (TaskJpa taskJpa) {
        return new TaskJpa(taskJpa.getDate(), taskJpa.getCategory(), taskJpa.getLanguage(), taskJpa.getTimeOut(), taskJpa.getUrl(), taskJpa.getCurrentStatus());
    }
}

