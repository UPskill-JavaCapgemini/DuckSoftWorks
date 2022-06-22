package LanguageDetection.application.DTO;

import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.model.ValueObjects.TaskResult;
import LanguageDetection.domain.model.ValueObjects.TimeOut;
import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.Task;
import LanguageDetection.domain.model.ValueObjects.TaskStatus;
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
    TaskStatus currentStatus;
    int timeOut;
    String category;

    public TaskDTO(Long id, Date date, InputUrl inputUrl, TaskResult taskResult, TaskStatus currentStatus, TimeOut timeOut, Category category) {
        this.id = id;
        this.date = date;
        this.inputUrl = inputUrl.toString();
        this.taskResult = taskResult;
        this.currentStatus = currentStatus;
        this.timeOut = timeOut.getTimeOut();
        this.category = category.getCategoryName().getCategoryName();
    }

}
