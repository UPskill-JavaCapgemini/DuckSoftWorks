package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.domain.model.ValueObjects.CategoryName;
import LanguageDetection.domain.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Represents the CategoryJpaRepository. Extends the CrudRepository Interface
 */
public interface CategoryJpaRepository extends CrudRepository<Category, CategoryName> {

    Optional<Category> findByCategoryName(CategoryName categoryName);

    int deleteByCategoryNameAndIsBaseCategoryFalse(CategoryName category_Name);

    boolean existsByCategoryName(CategoryName categoryName);
}
