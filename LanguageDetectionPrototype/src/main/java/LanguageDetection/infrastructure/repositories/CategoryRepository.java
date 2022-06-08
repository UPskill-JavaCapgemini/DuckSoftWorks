package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.ICategory;
import LanguageDetection.infrastructure.repositories.JPARepositories.CategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository implements ICategory {


    @Autowired
    CategoryJpaRepository categoryJpaRepository;



    /*

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
            String categoryByParameter = category.getCategoryName().getCategoryName();
            String categoryRepo = category1.getCategoryName().getCategoryName();
            return categoryRepo.equals(categoryByParameter);
        }
        return false;
    }*/


    /*public CategoryName save(Category category) {
        Category category2 = categoria.saveCategory(category);

    }*/

    @Override
    public Optional<Category> findCategoryById(Category category) {
        return categoryJpaRepository.findByCategoryName(category.getCategoryName());
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryJpaRepository.save(category);
    }

    @Override
    @Transactional
    public boolean deleteByName(Category category) {

        Optional<Category> categoryRepo = categoryJpaRepository.findByCategoryName(category.getCategoryName());
        if (!isBaseCategory(categoryRepo)) {
            categoryJpaRepository.deleteCategory(category.getCategoryName());
            return true;
        }
        return false;
    }

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryJpaRepository.findAll();
    }

    protected boolean isBaseCategory(Optional<Category> category) {
        boolean isBase = false;

        if (category.isPresent()) {
            switch (category.get().toString().toLowerCase()) {
                case "economia", "filosofia", "mecânica", "nutricionismo", "desporto":
                    isBase = true;
                    break;

                default:
                    isBase = false;
                    break;
            }
        }
        return isBase;
    }
}

