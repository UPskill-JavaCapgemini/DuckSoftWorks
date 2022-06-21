package LanguageDetection.domain.DomainService;

import LanguageDetection.domain.model.ValueObjects.CategoryName;
import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Represents the CategoryService.The domain service containing the validation logic for Category related functionalities
 */
@Component
public class CategoryService {

    @Autowired
    ICategoryRepository iCategoryRepository;

    /**
     * This method attempts to save a Category.
     * It will save the new Category if the Category hasn't yet been persisted
     *
     * @param category the Category to be persisted in the database
     * @return the saved Category if saving was successful
     */
    public Category saveCategory(Category category) {
        return iCategoryRepository.saveCategory(category);
    }

    /**
     * This method fetches information for all persisted categories in the database and returns a list containing them
     *
     * @return a Category list
     */
    public List<Category> findAll() {
        return iCategoryRepository.findAll();
    }

    /**
     * This method deletes a persisted Category by CategoryName if it is persisted in the database
     * Returns a boolean that indicates if the deletion operation was successful
     *
     *
     * @param categoryName the CategoryName containing the information about the Category to be deleted
     * @return true if the Category has been successfully deleted, false if not
     */
    public boolean deleteByName(CategoryName categoryName) {
        return iCategoryRepository.deleteByName(categoryName);
    }

    /**
     * This method fetches a single entry of a persisted Category by CategoryName if it is persisted in the database
     * Returns an Optional wrapping the found category if successful or an empty Optional if not
     *
     *
     * @param categoryName the CategoryName containing the information about the Category to be retrieved from the database
     * @return an Optional of Category if found or an empty Optional if no Category is found
     */
    public Optional<Category> findCategoryByName(CategoryName categoryName) {
        return iCategoryRepository.findCategoryByCategoryName(categoryName);
    }
}
