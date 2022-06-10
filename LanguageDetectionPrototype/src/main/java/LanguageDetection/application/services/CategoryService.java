package LanguageDetection.application.services;

import LanguageDetection.application.DTO.CategoryDTO;
import LanguageDetection.application.DTO.DTOAssemblers.CategoryDomainDTOAssembler;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.ICategory;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Represents the category service responsible for creating a category.
 *
 * @author DuckSoftWorks
 */

@Service
public class CategoryService {

    @Autowired
    CategoryDomainDTOAssembler dtoAssembler;

    @Autowired
    ICategory iCategory;

    /**
     * Creates a new category with a NewCategoryInfoDTO received by parameter.
     * The method verifies if category already exists in the repository before
     * the creation with the method findCategoryById.
     *
     * @param infoDTO the NewTaskInfoDTO object that contains a String that will be the CategoryName.
     * @return CategoryDTO assembled by CategoryDomainDTOAssembler wrapped by an Optional.
     */

    public Optional<CategoryDTO> createAndSaveCategory(NewCategoryInfoDTO infoDTO) {
        Category category = new Category(infoDTO.getCategory());
        Optional<Category> findCategory = iCategory.findCategoryById(category);
        if (findCategory.isEmpty()) {
            Category categoryRepo = iCategory.saveCategory(category);
            return Optional.of(dtoAssembler.toDTO(categoryRepo));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Method that allows the search for all the category in the repository
     * @return CategoryDTO list
     */

    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = iCategory.findAll();

        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        for (Category category : categories) {
            CategoryDTO categoryDTO = dtoAssembler.toDTO(category);
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

    /**
     * Method that allows to delete one of the category inside the repository
     * @param category
     * @return if the category was successfully eliminated returns true
     */

    public boolean deleteCategory(NewCategoryInfoDTO category) {
        Category duplicatedCategory = new Category(category.getCategory());
        return iCategory.deleteByName(duplicatedCategory);
    }

    /**
     * Method that allows the search in the repository of a category by its ID(CategoryName)
     * @param category
     * @return if the category was successfully found it returns an Optional with the category
     */

    public Optional<Category> findById(Category category) {
        Optional<Category> opCategoryRepo = iCategory.findCategoryById(category);

        return opCategoryRepo;
    }
}
