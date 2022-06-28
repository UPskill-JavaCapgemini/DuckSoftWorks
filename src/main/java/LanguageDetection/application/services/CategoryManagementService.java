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
 * Represents the CategoryManagementService. The applicational service for Category functionalities
 */

@Service
public class CategoryManagementService {

    @Autowired
    CategoryDomainDTOAssembler dtoAssembler;

    @Autowired
    CategoryService categoryService;

    /**
     * This method attempts to create and save a Category with the information provided by the admin.
     *
     * @param infoDTO the NewCategoryInfoDTO containing the information about the Category to be created and saved
     * @return CategoryDTO  CategoryDTO assembled through the CategoryDomainDTOAssembler wrapped in an Optional if successful or an empty Optional
     */
    public Optional<CategoryDTO> createAndSaveCategory(NewCategoryInfoDTO infoDTO) {
        try{
            Category category = new Category(infoDTO.getCategory());
            Optional<Category> categoryRepo = categoryService.saveCategory(category);
            if(categoryRepo.isPresent()){
                return Optional.of(dtoAssembler.toDTO(categoryRepo.get()));
            }
            return Optional.empty();
        } catch (NullPointerException | IllegalArgumentException e){
            return Optional.empty();
        }
    }

    /**
     * This method fetches information for all categories persisted in the database and returns a list of CategoryDTO containing them
     *
     * @return a CategoryDTO list
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
     * This method deletes a persisted Category if it is persisted in the database
     * Returns a boolean that indicates if the deletion operation was successful
     *
     *
     * @param category the NewCategoryInfoDTO containing the information about the Category to be deleted
     * @return true if the Category has been successfully deleted, false if not
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
