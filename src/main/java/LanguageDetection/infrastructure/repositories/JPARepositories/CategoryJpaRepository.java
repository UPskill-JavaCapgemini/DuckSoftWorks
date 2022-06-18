package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.domain.model.ValueObjects.CategoryName;
import LanguageDetection.domain.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryJpaRepository extends CrudRepository<Category, CategoryName> {

    Optional<Category> findByCategoryName(CategoryName categoryName);

    int deleteByCategoryNameAndIsBaseCategoryFalse(CategoryName category_Name);
}
