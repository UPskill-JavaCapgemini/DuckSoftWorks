package LanguageDetection.domain.model;

import LanguageDetection.domain.DomainService.BlackListService;
import LanguageDetection.domain.DomainService.CategoryService;
import LanguageDetection.domain.model.ValueObjects.CategoryName;
import LanguageDetection.domain.model.ValueObjects.InputUrl;
import LanguageDetection.domain.model.ValueObjects.TimeOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.Optional;

import static LanguageDetection.domain.DomainService.UserDetailsDomainService.getUserNameId;

@Component
/**
 * Represents the TaskFactory. It validates if Task creation is possible and creates a Task if so
 */
public class TaskFactory {

    @Autowired
    BlackListService blackListService;

    @Autowired
    CategoryService categoryService;

    /**
     * This method attempts to create a Task with a provided url, timeout and category name
     * It verifies if the provided category is existent and if the url hasn't been blacklisted prior to Task creation
     *
     * @param userInputUrl the url to be used for Task creation
     * @param userTimeOut the timeout to be used for Task creation
     * @param userCategoryName the category name to be used for task creation
     * @throws MalformedURLException if the provided url is does not have an HTTP protocol or if parsing the url was unsuccessful
     * @return a Task wrapped in an Optional if the Task was created successfully, an empty Optional if not
     */
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
