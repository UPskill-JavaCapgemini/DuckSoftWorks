package LanguageDetection.application.services;

import LanguageDetection.application.DTO.CategoryDTO;
import LanguageDetection.application.DTO.DTOAssemblers.CategoryDomainDTOAssembler;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.domain.DomainService.CategoryService;
import LanguageDetection.domain.model.ValueObjects.CategoryName;
import LanguageDetection.domain.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Represents the category service responsible for creating a category.
 *
 * @author DuckSoftWorks
 */

@Service
public class CategoryManagementService {

    @Autowired
    CategoryDomainDTOAssembler dtoAssembler;

    @Autowired
    CategoryService categoryService;
    /**
     * Creates a new category with a NewCategoryInfoDTO received by parameter.
     * The method verifies if category already exists in the repository before
     * the creation with the method findCategoryById.
     *
     * @param infoDTO the NewCategoryInfoDTO object that contains a String that will be the CategoryName.
     * @return CategoryDTO assembled by CategoryDomainDTOAssembler wrapped by an Optional.
     */

    public Optional<CategoryDTO> createAndSaveCategory(NewCategoryInfoDTO infoDTO) {
        try{
            Category category = new Category(infoDTO.getCategory());
            Category categoryRepo = categoryService.saveCategory(category);
            return Optional.of(dtoAssembler.toDTO(categoryRepo));
        } catch (NullPointerException | IllegalArgumentException e){
            return Optional.empty();
        }
    }

    /**
     * Method that allows the search for all the categories in the repository
     * @return CategoryDTO list
     */

    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryService.findAll();

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
        try {
            CategoryName categoryName = new CategoryName(category.getCategory());
            return categoryService.deleteByName(categoryName);
        }catch (IllegalArgumentException | NullPointerException e){
            return false;
        }
    }

}
