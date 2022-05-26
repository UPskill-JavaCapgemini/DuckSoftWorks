package LanguageDetection.domain.factories;


import LanguageDetection.domain.entities.example.Task;

import java.util.Date;


public interface ITaskFactory {

    public Task createTask(String name);

}
