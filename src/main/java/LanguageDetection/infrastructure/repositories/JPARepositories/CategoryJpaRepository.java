package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.domain.ValueObjects.CategoryName;
import LanguageDetection.domain.entities.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryJpaRepository extends CrudRepository<Category, String> {

    Optional<Category> findByCategoryName(CategoryName categoryName);

    @Modifying
    @Query(value = "DELETE FROM CATEGORY WHERE CATEGORY_NAME= ?1 AND IS_BASE_CATEGORY = 0", nativeQuery = true)
    int deleteCategoryIfNotBase(String category_name);
}
