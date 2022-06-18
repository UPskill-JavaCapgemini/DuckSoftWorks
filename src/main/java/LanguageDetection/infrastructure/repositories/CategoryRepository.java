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
 * Represents the category repository responsible for handling all interactions with DB of this Domain object.
 * Implements the ICategoryRepository interface to allow decoupling and easier transition to other DB in the future.
 *
 * @author DuckSoftWorks Team
 */

@Repository
public class CategoryRepository implements ICategoryRepository {


    @Autowired
    CategoryJpaRepository categoryJpaRepository;

    /**
     * Method that allows the search of a category by its ID
     *
     * @param categoryName
     * @return an Optinal of the found category
     */
    @Override
    public Optional<Category> findCategoryByCategoryName(CategoryName categoryName) {
        return categoryJpaRepository.findByCategoryName(categoryName);
    }

    /**
     * Method that allows to save a category to the DB
     *
     * @param category
     * @return the saved category
     */

    @Override
    public Category saveCategory(Category category) {
        return categoryJpaRepository.save(category);
    }

    /**
     * Method that allows to delete a category by its name from DB
     *
     * @param categoryName
     * @return true if it was sucessfully deleted
     */
    @Override
    @Transactional
    public boolean deleteByName(CategoryName categoryName) {
        int verify = categoryJpaRepository.deleteByCategoryNameAndIsBaseCategoryFalse(categoryName);
        return verify >= 1;
    }

    /**
     * Method that returns all the category found on DB in a list
     *
     * @return a list with all the category found
     */

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryJpaRepository.findAll();
    }

    /**
     * Method that checks if the category is part of the group that is protected from delete.
     * @param category
     * @return true if the category cannot be deleted.
     */
}

