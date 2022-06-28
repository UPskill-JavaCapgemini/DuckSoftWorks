package LanguageDetection.domain.model;

import LanguageDetection.domain.model.ValueObjects.CategoryName;
import LanguageDetection.domain.shared.AggregateRoot;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents a Task Category
 */
@Component
@Entity
@Table(name = "TaskCategory")
public class Category implements AggregateRoot<CategoryName> {

    /**
     * Gets the Value Object CategoryName where the business validations are implemented.
     */
    @Getter
    @EmbeddedId
    CategoryName categoryName;


    private boolean isBaseCategory = false;


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
     */
    public void defineAsBaseCategory() {
       this.isBaseCategory = true;
    }

    /**
     * This method verifies if a Category is a base Category
     * Returns a boolean to indicate this
     *
     * @return true if it is a base Category, false if not
     */
    public boolean isBaseCategory(){
        return this.isBaseCategory;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }


    /**
     * This method represents a Category textually
     * Returns a String with the Category information
     *
     * @return a String with a textual representation of the Category
     */
    @Override
    public String toString() {
        return categoryName.toString();
    }

    /**
     * This method returns the Category identity
     *
     * @return  a CategoryName as the identity of Category
     */
    @Override
    public CategoryName identity() {
        return this.categoryName;
    }

}
