package LanguageDetection.application.DTO;

import LanguageDetection.domain.ValueObjects.CategoryName;
import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.ValueObjects.TaskResult;
import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.Task;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class TaskDTO {
    Long id;
    Date date;
    String inputUrl;
    TaskResult taskResult;
    Task.TaskStatus currentStatus;
    int timeOut;
    String category;

    public TaskDTO(Long id, Date date, InputUrl inputUrl, TaskResult taskResult, Task.TaskStatus currentStatus, TimeOut timeOut, Category category) {
        this.id = id;
        this.date = date;
        this.inputUrl = inputUrl.getUrl();
        this.taskResult = taskResult;
        this.currentStatus = currentStatus;
        this.timeOut = timeOut.getTimeOut();
        this.category = category.getCategoryName().getCategoryName();
    }

    @Override
    public String toString() {
        return "Task :" +
                " id=" + id +
                ", date=" + date +
                ", inputUrl=" + inputUrl +
                ", language=" + taskResult +
                ", currentStatus=" + currentStatus +
                ", timeOut=" + timeOut +
                ", category=" + category +
                '\n';
    }
}
