package LanguageDetection.domain.DomainService;

import LanguageDetection.domain.model.ValueObjects.CategoryName;
import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryService {

    @Autowired
    ICategoryRepository iCategoryRepository;

    public Category saveCategory(Category category) {
        return iCategoryRepository.saveCategory(category);
    }

    public List<Category> findAll() {
        return iCategoryRepository.findAll();
    }

    public boolean deleteByName(CategoryName categoryName) {
        return iCategoryRepository.deleteByName(categoryName);
    }

    public Optional<Category> findCategoryByName(CategoryName categoryName) {
        return iCategoryRepository.findCategoryByCategoryName(categoryName);
    }
}
