package LanguageDetection.application.DTO;

import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.model.ValueObjects.TaskResult;
import LanguageDetection.domain.model.ValueObjects.TaskStatus;
import LanguageDetection.domain.model.ValueObjects.TimeOut;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
/**
 * Represents a TaskDTO containing the task id, Date in which it was persisted , InputUrl , TaskResult, TaskStatus ,TimeOut and Category
 */
public class TaskDTO {
    Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd-MM-yyyy", timezone = "GMT+1")
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
