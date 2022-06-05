package LanguageDetection.infrastructure.repositories;

import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.infrastructure.datamodel.CategoryJpa;
import LanguageDetection.infrastructure.datamodel.DataAssemblers.CategoryDomainDataAssembler;
import LanguageDetection.infrastructure.repositories.JPARepositories.CategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepository {

    @Autowired
    CategoryDomainDataAssembler categoryAssembler;


    @Autowired
    CategoryJpaRepository categoryJpaRepository;


    public Category save(Category category) throws MalformedURLException {
        CategoryJpa categoryJpa = categoryAssembler.toData(category);

        CategoryJpa savedCategoryJpa = categoryJpaRepository.save(categoryJpa);

        return categoryAssembler.toDomain(savedCategoryJpa);
    }

    public List<Category> findAll() {
        List<CategoryJpa> categoryJpas = (List<CategoryJpa>) categoryJpaRepository.findAll();

        List<Category> categories = new ArrayList<>();
        for (CategoryJpa categoryJpa : categoryJpas) {
            Category category = categoryAssembler.toDomain(categoryJpa);
            categories.add(category);
        }

        return categories;
    }

    @Transactional
    public boolean delete(Category category) {
        CategoryJpa categoryJpa = categoryAssembler.toData(category);

        if(isCategoryOnRepository(category)){
            categoryJpaRepository.deleteCategory(categoryJpa.getCategoryName());
            return true;
        } else {
            return false;
        }
    }

    protected boolean isCategoryOnRepository(Category category){
        List<Category> categories = findAll();
        for (Category category1 : categories) {
            String categoryByParameter = category.getCategoryDescription().getCategoryDescription();
            String categoryRepo = category1.getCategoryDescription().getCategoryDescription();
            return categoryRepo.equals(categoryByParameter);
        }
        return false;
    }
}
