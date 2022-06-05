package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.CategoryDescription;
import LanguageDetection.domain.shared.Entity;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Class that will provide the category choosen by the user that serves a text
 */
public class Category implements Entity {

    /*  {
        Economics,
        Philosophy,
        Mechanics,
        Nutrition,
        Sport
    }*/
    @Getter
    CategoryDescription categoryDescription;

    public Category(String categoryDescription) {

        this.categoryDescription = new CategoryDescription(categoryDescription);
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Object identity() {
        return this.categoryDescription;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return 0;
    }

    @Override
    public String toString() {
        return categoryDescription.toString();
    }
}
