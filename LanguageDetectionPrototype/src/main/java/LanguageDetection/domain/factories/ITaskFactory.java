package LanguageDetection.domain.factories;


import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.ValueObjects.URL;
import LanguageDetection.domain.entities.Task;

import java.util.Date;


public interface ITaskFactory {

    public Task createTask(URL url, Task.Category category, TimeOut timeOut);

}
