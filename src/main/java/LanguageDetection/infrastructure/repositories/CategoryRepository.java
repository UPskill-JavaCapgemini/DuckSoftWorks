package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.model.ValueObjects.CategoryName;
import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.ICategoryRepository;
import LanguageDetection.infrastructure.repositories.JPARepositories.CategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Represents the CategoryRepository. The implementation of ICategoryRepository.
 * Handles all interactions with the database of this domain entity
 */

@Repository
public class CategoryRepository implements ICategoryRepository {


    @Autowired
    CategoryJpaRepository categoryJpaRepository;

    /**
     * Method that allows the search of a category by its CategoryName
     *
     * @param categoryName the CategoryName of a Category
     * @return an Optional of a Category if found, an empty Optional if not
     */
    @Override
    public Optional<Category> findCategoryByCategoryName(CategoryName categoryName) {
        return categoryJpaRepository.findByCategoryName(categoryName);
    }

    /**
     * Method that attempts to save a category
     *
     * @param category the Category to be persisted in the database
     * @return the saved BlackListItem, if saving was successful
     */

    @Override
    public Category saveCategory(Category category) {
        return categoryJpaRepository.save(category);
    }

    /**
     * This method deletes a persisted Category if it has been persisted in the database and is not a base Category
     * Returns a boolean that indicates if the deletion operation was successful
     *
     * @param categoryName the CategoryName containing the information about the Category to be deleted
     * @return true if it was sucessfully deleted, false if not
     */
    @Override
    @Transactional
    public boolean deleteByName(CategoryName categoryName) {
        int verify = categoryJpaRepository.deleteByCategoryNameAndIsBaseCategoryFalse(categoryName);
        return verify >= 1;
    }

    /**
     * This method fetches information for all Category items persisted in the database and returns a list containing them
     *
     * @return a Category list
     */
    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryJpaRepository.findAll();
    }

    public boolean existsByCategoryName(CategoryName categoryName){
        return categoryJpaRepository.existsByCategoryName(categoryName);
    }
}

