package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.CategoryDescription;
import LanguageDetection.domain.shared.Entity;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import javax.persistence.EmbeddedId;
import javax.persistence.Table;

/**
 * Class that that will be represent a text.
 * The cateory of the text will be choosen by the user.
 */
@Repository
@javax.persistence.Entity
@Table
public class Category implements Entity {

    /*  {
        Economics,
        Philosophy,
        Mechanics,
        Nutrition,
        Sport
    }*/

    /**
     * Gets the Value Object CategoryDescription where the business validations are implemented.
     */

    @Getter
    @EmbeddedId
    CategoryDescription categoryDescription;

    public void setCategoryDescription(CategoryDescription categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public Category() {
    }

    /**
     * Constructs a new category.
     */
    public Category(String categoryDescription) {

        this.categoryDescription = new CategoryDescription(categoryDescription);
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    /**
     * method that identify the category
     * @return the description of the category
     */
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
