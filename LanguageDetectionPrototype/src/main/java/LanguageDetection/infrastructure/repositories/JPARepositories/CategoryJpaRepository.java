package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.domain.ValueObjects.CategoryName;
import LanguageDetection.domain.entities.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryJpaRepository extends CrudRepository<Category,String> {

    @Modifying
    @Query(value = "DELETE FROM CATEGORY c WHERE c.CATEGORY_NAME = ?1", nativeQuery = true)
    void deleteCategory(CategoryName categoryName);

    Optional<Category> findByCategoryName(CategoryName categoryName);
}
