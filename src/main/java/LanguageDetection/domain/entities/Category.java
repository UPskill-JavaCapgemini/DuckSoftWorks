package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.CategoryName;
import LanguageDetection.domain.shared.AggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that that will represent a text.
 * The cateory of the text will be choosen by the user.
 */
@Component
@Entity
@Table
public class Category implements AggregateRoot<CategoryName> {

    /**
     * Gets the Value Object CategoryName where the business validations are implemented.
     */

    @Getter
    @EmbeddedId
    CategoryName categoryName;

    private static boolean isBaseCategory = false;


    protected Category() {
    }

    /**
     * Constructs a new category.
     */
    public Category(String categoryDescription) {

        this.categoryName = new CategoryName(categoryDescription);
    }

    /**
     * Method to be used when bootstrapping data to define Base Categories that cannot be deleted.
     * @param baseCategory
     */
    public static void defineAsBaseCategory(Category baseCategory) {
       baseCategory.isBaseCategory = true;
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
    public String toString() {
        return categoryName.toString();
    }

    @Override
    public CategoryName identity() {
        return this.categoryName;
    }

    @Override
    public boolean hasIdentity(CategoryName id) {
        return AggregateRoot.super.hasIdentity(id);
    }
}
