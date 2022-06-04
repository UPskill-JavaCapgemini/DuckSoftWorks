package LanguageDetection.domain.factories;

public class CategoryFactory implements ICategoryFactory {


    @Override
    public Category createCategory(String category) {
        return new Category(category);
    }
}
