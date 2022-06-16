package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.CategoryName;

import java.util.List;
import java.util.Optional;


public interface ICategoryRepository {

    Category saveCategory(Category category);

    boolean deleteByName(String category);

    List<Category> findAll();

    Optional<Category> findCategoryById(CategoryName CategoryName);


}
