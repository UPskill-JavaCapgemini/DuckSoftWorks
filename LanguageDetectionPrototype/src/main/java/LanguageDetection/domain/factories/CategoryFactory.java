package LanguageDetection.domain.factories;

import LanguageDetection.domain.entities.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryFactory implements ICategoryFactory {


    @Override
    public Category createCategory(String category) {

        return new Category(category);
    }
}
