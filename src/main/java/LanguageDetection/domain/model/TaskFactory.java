package LanguageDetection.domain.model;

import LanguageDetection.domain.DomainService.BlackListService;
import LanguageDetection.domain.DomainService.CategoryService;
import LanguageDetection.domain.DomainService.UserDetailsDomainService;
import LanguageDetection.domain.model.ValueObjects.CategoryName;
import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.model.ValueObjects.TimeOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;

import java.util.Optional;

import static LanguageDetection.domain.DomainService.UserDetailsDomainService.getUserNameId;

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
                Task task = new Task(inputUrl, timeOut, categoryRepository.get(), getUserNameId());

                return Optional.of(task);
            }
            return Optional.empty();

        } catch (IllegalArgumentException | MalformedURLException | NullPointerException e) {
            return Optional.empty();
        }
    }
}
