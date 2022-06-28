package LanguageDetection.domain.model;

import LanguageDetection.domain.model.ValueObjects.CategoryName;

import java.util.List;
import java.util.Optional;

/**
 * Represents the ICategoryRepository
 */
public interface ICategoryRepository {

    Category saveCategory(Category category);

    boolean deleteByName(CategoryName category);

    List<Category> findAll();

    Optional<Category> findCategoryByCategoryName(CategoryName CategoryName);

}

