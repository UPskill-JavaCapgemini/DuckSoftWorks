package LanguageDetection.infrastructure.repositories.JPARepositories;

import LanguageDetection.infrastructure.datamodel.CategoryJpa;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryJpaRepository extends CrudRepository<CategoryJpa,Long> {

    @Modifying
    @Query(value = "DELETE FROM CATEGORY c WHERE c.CATEGORY_NAME = ?1", nativeQuery = true)
    void deleteCategory(String categoryName);
}
