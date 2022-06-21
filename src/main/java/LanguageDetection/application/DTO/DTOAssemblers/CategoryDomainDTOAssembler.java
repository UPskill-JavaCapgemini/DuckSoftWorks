package LanguageDetection.application.DTO.DTOAssemblers;

import LanguageDetection.application.DTO.CategoryDTO;
import LanguageDetection.domain.model.Category;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
/**
 * Represents the CategoryDomainDTOAssembler. The assembler of a CategoryDTO by input of a Category
 */
public class CategoryDomainDTOAssembler {

    /**
     * This method creates an instance of a CategoryDTO by using a Category
     *
     * @param category a Category containing the data that was inputted by the admin
     * @return a new instance of CategoryDTO
     */
    public CategoryDTO toDTO(Category category){
        return new CategoryDTO(category);
    }
}
