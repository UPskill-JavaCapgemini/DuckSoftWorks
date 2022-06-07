package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.CategoryDescription;
import org.springframework.data.repository.CrudRepository;


public interface ICategoria {

    Category saveCategory(Category category);

    boolean deleteByDescription(CategoryDescription categoryDescription);
}
