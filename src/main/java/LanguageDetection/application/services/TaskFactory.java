package LanguageDetection.application.services;

import LanguageDetection.domain.DomainService.BlackListService;
import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;

import java.util.Optional;

@Component
public class TaskFactory {

    @Autowired
     BlackListService blackListService;
    public Optional<Task> createTask(InputUrl inputUrl, TimeOut timeOut, Category category) throws MalformedURLException {
        if (!blackListService.isBlackListed(inputUrl))
        {
            Task task = new Task(inputUrl.getUrl(),timeOut.getTimeOut(),category);
            return Optional.of(task);
        }
        return Optional.empty();
    }
}
