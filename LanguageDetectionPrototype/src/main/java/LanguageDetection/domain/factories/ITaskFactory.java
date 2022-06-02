package LanguageDetection.domain.factories;



import LanguageDetection.domain.entities.Task;

import java.net.MalformedURLException;


public interface ITaskFactory {

    public Task createTask(String url, int timeOut, String category) throws MalformedURLException;

}
