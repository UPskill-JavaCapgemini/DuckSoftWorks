package LanguageDetection.domain.entities;

import java.util.List;
import java.util.Optional;


public interface ICategoryRepository {

    Category saveCategory(Category category);

    boolean deleteByName(String category);

    List<Category> findAll();

    Optional<Category> findCategoryById(Category category);

    Optional<Category> findCategoryById(String categoryName);

}
