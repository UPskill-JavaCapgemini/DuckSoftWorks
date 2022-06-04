package LanguageDetection.application.services;

import LanguageDetection.application.DTO.CategoryDTO;
import LanguageDetection.application.DTO.DTOAssemblers.CategoryDomainDTOAssembler;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.factories.ICategoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    ICategoryFactory categoryFactory;

    @Autowired
    CategoryDomainDTOAssembler dtoAssembler;

    @Autowired
    CategoryRepository categoryRepository;


    public CategoryDTO createCategory(NewCategoryInfoDTO infoDTO){
        Category category = categoryFactory.createCategory(infoDTO.getCategory());
        Category categoryRepo = categoryRepository.save(category);
        return dtoAssembler.toDTO(categoryRepo);
    }

}
