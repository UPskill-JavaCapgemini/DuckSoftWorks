package LanguageDetection.domain.factories;


import LanguageDetection.domain.entities.example.Task;


public interface ITaskFactory {

    public Task createTask(String name);

}
