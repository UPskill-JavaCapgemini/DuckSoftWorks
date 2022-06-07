package LanguageDetection.application.services;

import LanguageDetection.application.DTO.CategoryDTO;
import LanguageDetection.application.DTO.DTOAssemblers.CategoryDomainDTOAssembler;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.ICategoria;
import LanguageDetection.infrastructure.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryDomainDTOAssembler dtoAssembler;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ICategoria categoria;


    public CategoryDTO createCategory(NewCategoryInfoDTO infoDTO) throws MalformedURLException {


        Category category = new Category(infoDTO.getCategory());
        Category categoryRepo = categoria.saveCategory(category);
        return dtoAssembler.toDTO(categoryRepo);
    }

    /*public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        for (Category category : categories) {
            CategoryDTO categoryDTO = dtoAssembler.toDTO(category);
            categoryDTOS.add(categoryDTO);
        }

        return categoryDTOS;
    }

    public boolean deleteCategory(NewCategoryInfoDTO category) {
        Category duplicatedCategory = categoryFactory.createCategory(category.getCategory());
        return categoryRepository.delete(duplicatedCategory);
    }*/

}
