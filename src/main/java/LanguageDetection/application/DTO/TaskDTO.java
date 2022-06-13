package LanguageDetection.application.DTO;

import LanguageDetection.domain.ValueObjects.InputUrl;
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
    Task.Language language;
    Task.TaskStatus currentStatus;
    TimeOut timeOut;
    Category category;

    public TaskDTO(Long id, Date date, InputUrl inputUrl, Task.Language language, Task.TaskStatus currentStatus, TimeOut timeOut, Category category) {
        this.id = id;
        this.date = date;
        this.inputUrl = inputUrl;
        this.language = language;
        this.currentStatus = currentStatus;
        this.timeOut = timeOut;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Task :" +
                "date=" + date +
                ", inputUrl=" + inputUrl +
                ", language=" + language +
                ", currentStatus=" + currentStatus +
                ", timeOut=" + timeOut +
                ", category=" + category +
                '\n';
    }
}
