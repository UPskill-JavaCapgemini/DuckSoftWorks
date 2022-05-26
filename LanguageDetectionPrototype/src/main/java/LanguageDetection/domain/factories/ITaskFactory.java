package LanguageDetection.domain.factories;


import LanguageDetection.domain.entities.example.Task;


public interface ITaskFactory {

    public Task createExample(String name);
    public Task createExample(Long id, String name);

}
