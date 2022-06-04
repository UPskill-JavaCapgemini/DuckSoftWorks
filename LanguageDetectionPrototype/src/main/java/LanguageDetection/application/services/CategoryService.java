package LanguageDetection.application.services;

import LanguageDetection.application.DTO.CategoryDTO;
import LanguageDetection.application.DTO.DTOAssemblers.CategoryDomainDTOAssembler;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.factories.ICategoryFactory;
import LanguageDetection.infrastructure.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    ICategoryFactory categoryFactory;

    @Autowired
    CategoryDomainDTOAssembler dtoAssembler;

    @Autowired
    CategoryRepository categoryRepository;



    public CategoryDTO createCategory(NewCategoryInfoDTO infoDTO) throws MalformedURLException {


        Category category = categoryFactory.createCategory(infoDTO.getCategory());
        Category categoryRepo = categoryRepository.save(category);
        return dtoAssembler.toDTO(categoryRepo);
    }



}
