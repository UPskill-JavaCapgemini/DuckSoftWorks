package LanguageDetection.application.DTO.DTOAssemblers;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.domain.model.BlackListItem;
import org.springframework.stereotype.Service;

@Service
/**
 * Represents the BlackListDomainDTOAssembler. The assembler of a BlackListDTO by input of a BlackListItem
 */
public class BlackListDomainDTOAssembler {

    private BlackListDomainDTOAssembler(){}

    /**
     * This method creates an instance of a BlackListDTO by using a BlackListItem
     *
     * @param blackList a BlackListItem containing the data that was inputted by the admin
     * @return a new instance of BlackListDTO
     */
    public BlackListDTO toDTO(BlackListItem blackList){
        return new BlackListDTO(blackList);
    }
}
