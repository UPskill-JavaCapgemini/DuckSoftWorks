package LanguageDetection.application.services;

import LanguageDetection.application.DTO.CategoryDTO;
import LanguageDetection.application.DTO.DTOAssemblers.CategoryDomainDTOAssembler;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.ICategoryRepository;
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
public class CategoryService {

    @Autowired
    CategoryDomainDTOAssembler dtoAssembler;

    @Autowired
    ICategoryRepository iCategoryRepository;
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
            Category categoryRepo = iCategoryRepository.saveCategory(category);
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
        List<Category> categories = iCategoryRepository.findAll();

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
        return iCategoryRepository.deleteByName(category.getCategory());
    }

    /**
     * Method that allows the search in the repository of a category by its ID(CategoryName)
     * @param category
     * @return if the category was successfully found it returns an Optional with the category
     */

    public Optional<Category> findCategoryByName(Category category) {
        Optional<Category> opCategoryRepo = iCategoryRepository.findCategoryById(category);

        return opCategoryRepo;
    }
}
