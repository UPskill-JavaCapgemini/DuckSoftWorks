package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.domain.ValueObjects.CategoryDescription;
import LanguageDetection.domain.entities.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CategoryJpaRepository extends CrudRepository<Category,String> {

    @Modifying
    @Query(value = "DELETE FROM CATEGORY c WHERE c.CATEGORY_DESCRIPTION = ?1", nativeQuery = true)
    void deleteCategory(CategoryDescription categoryDescription);

    Optional<Category> findByCategoryDescription(CategoryDescription categoryDescription);
}
