package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.CategoryDescription;

import java.util.List;


public interface ICategory {

    Category saveCategory(Category category);

    boolean deleteByDescription(Category category);

    List<Category> findAll();
}
