package LanguageDetection.domain.factories;


import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.entities.Task;

import java.util.Date;


public interface ITaskFactory {

    public Task createTask(InputUrl url, Task.Category category, TimeOut timeOut);

}
