package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.CategoryName;
import LanguageDetection.domain.shared.AggregateRoot;
import LanguageDetection.domain.shared.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EmbeddedId;
import javax.persistence.Table;

/**
 * Class that that will represent a text.
 * The cateory of the text will be choosen by the user.
 */
@Component
@javax.persistence.Entity
@Table
public class Category implements AggregateRoot {

    /**
     * Gets the Value Object CategoryName where the business validations are implemented.
     */

    @Getter
    @EmbeddedId
    CategoryName categoryName;


    protected Category() {
    }

    /**
     * Constructs a new category.
     */
    public Category(String categoryDescription) {

        this.categoryName = new CategoryName(categoryDescription);
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    /**
     * method that identify the category
     * @return the name of the category
     */
    @Override
    public Object identity() {
        return this.categoryName;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return 0;
    }

    @Override
    public String toString() {
        return categoryName.toString();
    }
}
