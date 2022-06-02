package LanguageDetection.domain.factories;


import LanguageDetection.domain.entities.Task;


public interface ITaskFactory {

    public Task createTask(String name, String language);

}
