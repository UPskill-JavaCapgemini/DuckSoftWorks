package LanguageDetection.domain.factories;

import LanguageDetection.domain.entities.Category;

public interface ICategoryFactory {

    public Category createCategory(String category);
}
