package LanguageDetection.application.services;

import LanguageDetection.application.DTO.CategoryDTO;
import LanguageDetection.application.DTO.DTOAssemblers.CategoryDomainDTOAssembler;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.ICategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryDomainDTOAssembler dtoAssembler;

    @Autowired
    ICategory iCategory;


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

    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = iCategory.findAll();

        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        for (Category category : categories) {
            CategoryDTO categoryDTO = dtoAssembler.toDTO(category);
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

    public boolean deleteCategory(NewCategoryInfoDTO category) {
        Category duplicatedCategory = new Category(category.getCategory());
        return iCategory.deleteByName(duplicatedCategory);
    }


    public Optional<Category> findById(Category category) {
        Optional<Category> opCategoryRepo = iCategory.findCategoryById(category);

        return opCategoryRepo;
    }
}
