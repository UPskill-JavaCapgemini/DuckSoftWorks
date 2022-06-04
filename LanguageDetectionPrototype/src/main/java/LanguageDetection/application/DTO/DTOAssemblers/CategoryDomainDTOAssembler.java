package LanguageDetection.application.DTO.DTOAssemblers;

import LanguageDetection.application.DTO.CategoryDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class CategoryDomainDTOAssembler {

    public CategoryDTO toDTO(Category category){
        return new CategoryDTO(category);
    }
}
