package LanguageDetection.application.DTO;

import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.ValueObjects.TaskResult;
import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.Task;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@EqualsAndHashCode
public class TaskDTO {
    Long id;
    Date date;
    InputUrl inputUrl;
    TaskResult taskResult;
    Task.TaskStatus currentStatus;
    TimeOut timeOut;
    Category category;

    public TaskDTO(Long id, Date date, InputUrl inputUrl, TaskResult taskResult, Task.TaskStatus currentStatus, TimeOut timeOut, Category category) {
        this.id = id;
        this.date = date;
        this.inputUrl = inputUrl;
        this.taskResult = taskResult;
        this.currentStatus = currentStatus;
        this.timeOut = timeOut;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Task :" +
                "date=" + date +
                ", inputUrl=" + inputUrl +
                ", language=" + taskResult +
                ", currentStatus=" + currentStatus +
                ", timeOut=" + timeOut +
                ", category=" + category +
                '\n';
    }
}
