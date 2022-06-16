package LanguageDetection.domain.factory;

import LanguageDetection.application.services.CategoryManagementService;
import LanguageDetection.domain.DomainService.BlackListService;
import LanguageDetection.domain.DomainService.CategoryService;
import LanguageDetection.domain.ValueObjects.CategoryName;
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

    @Autowired
    CategoryService categoryService;

    public Optional<Task> createTask(String userInputUrl, int userTimeOut, String userCategoryName) throws MalformedURLException {

        try {
            InputUrl inputUrl = new InputUrl(userInputUrl);
            TimeOut timeOut = new TimeOut(userTimeOut);
            CategoryName categoryName = new CategoryName(userCategoryName);
            Optional<Category> categoryRepository = categoryService.findCategoryByName(categoryName);

            if (!blackListService.isBlackListed(inputUrl) && categoryRepository.isPresent()) {
                Task task = new Task(inputUrl.getUrl(), timeOut.getTimeOut(), categoryRepository.get());
                return Optional.of(task);
            }
            return Optional.empty();

        } catch (IllegalArgumentException | MalformedURLException | NullPointerException e) {
            return Optional.empty();
        }
    }
}
